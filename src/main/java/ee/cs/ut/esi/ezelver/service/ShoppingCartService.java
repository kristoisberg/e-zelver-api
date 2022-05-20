package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.bpb.service.DeliveryOrderService;
import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.exception.NotFoundException;
import ee.cs.ut.esi.ezelver.model.*;
import ee.cs.ut.esi.ezelver.repository.ProductEntryRepository;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartItemRepository;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductEntryRepository productEntryRepository;
    private final UserService userService;
    private final DigitalStoreService digitalStoreService;
    private final FinancialService financialService;
    private final WarehouseService warehouseService;
    private final AuthenticationService authenticationService;
    private final DeliveryOrderService deliveryOrderService;

    public ShoppingCart createShoppingCart() {
        Optional<Customer> customer = userService.fetchCustomerById(authenticationService.getUserId());

        if (customer.isEmpty())
            throw new BusinessException("User doesn't exist for shopping cart creation.");

        ShoppingCart shoppingCart = new ShoppingCart(customer.get(), 0);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getOrCreateShoppingCart() {
        Optional<Customer> customer = userService.fetchCustomerById(authenticationService.getUserId());

        if (customer.isEmpty())
            throw new BusinessException("User doesn't exist for shopping cart creation.");

        Optional<ShoppingCart> shoppingCart = customer.get().getShoppingCarts().stream().filter(sc -> sc.getOrder() == null).findFirst();
        return shoppingCart.orElseGet(this::createShoppingCart);
    }

    public ShoppingCart fetchShoppingCartById(int shoppingCartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isEmpty())
            throw new NotFoundException("Shopping cart not found with this ID");

        return shoppingCart.get();
    }

    public ShoppingCartItem fetchShoppingCartItemById(int shoppingCartItemId) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId);

        if (shoppingCartItem.isEmpty())
            throw new NotFoundException("Shopping cart item not found with this ID");

        return shoppingCartItem.get();
    }

    public ShoppingCart deleteShoppingCartItem(int shoppingCartId, int cartItemId) {
        ShoppingCart shoppingCart = fetchShoppingCartById(shoppingCartId);
        ShoppingCartItem shoppingCartItem = fetchShoppingCartItemById(cartItemId);

        if (shoppingCart.getOrder() != null) {
            throw new BusinessException("Shopping cart is already processed.");
        }

        if (shoppingCart.getId() != shoppingCartItem.getShoppingCart().getId()) {
            throw new BusinessException("Item does not belong to shopping cart.");
        }

        ProductEntry productEntry = shoppingCartItem.getProductEntry();
        shoppingCartItemRepository.deleteById(cartItemId);

        productEntry.setQuantity(productEntry.getQuantity() + productEntry.getQuantity());
        productEntryRepository.save(productEntry);

        shoppingCart.setItems(shoppingCart.getItems().stream()
                .filter(item -> !item.getId().equals(cartItemId))
                .collect(Collectors.toList()));
        return shoppingCart;
    }

    public ShoppingCart setShoppingCartItemQuantity(int shoppingCartId, int cartItemId, int quantity) {
        ShoppingCart shoppingCart = fetchShoppingCartById(shoppingCartId);
        ShoppingCartItem shoppingCartItem = fetchShoppingCartItemById(cartItemId);

        if (shoppingCart.getOrder() != null) {
            throw new BusinessException("Shopping cart is already processed.");
        }

        if (shoppingCart.getId() != shoppingCartItem.getShoppingCart().getId()) {
            throw new BusinessException("Item does not belong to shopping cart.");
        }

        ProductEntry productEntry = shoppingCartItem.getProductEntry();
        int newRemainingQuantity = productEntry.getQuantity() + shoppingCartItem.getQuantity() - quantity;
        if (newRemainingQuantity < 0) {
            throw new BusinessException("Quantity is larger than stock.");
        }

        shoppingCartItem.setQuantity(quantity);
        shoppingCartItemRepository.save(shoppingCartItem);

        productEntry.setQuantity(newRemainingQuantity);
        productEntryRepository.save(productEntry);

        shoppingCart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .forEach(item -> item.setQuantity(quantity));
        return shoppingCart;
    }

    public List<ShoppingCartItem> getShoppingCartItems(int shoppingCartId) { // TODO: Is this clear all shopping cart items?
        List<ShoppingCartItem> result = shoppingCartItemRepository.findByShoppingCartId(shoppingCartId);
        result.forEach(item -> item.setShoppingCart(null));
        return result;
    }

    public ShoppingCart addItem(int shoppingCartId, int productEntryId, int quantity) {
        ShoppingCart shoppingCart = fetchShoppingCartById(shoppingCartId);
        ProductEntry productEntry = warehouseService.fetchProductById(productEntryId);

        if (shoppingCart.getOrder() != null) {
            throw new BusinessException("Shopping cart is already processed.");
        } else if (productEntry.getQuantity() <= 0) {
            throw new BusinessException("Item is out of stock.");
        } else if (quantity > productEntry.getQuantity()) {
            throw new BusinessException("Quantity is larger than stock.");
        }

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, productEntry, quantity);
        shoppingCartItemRepository.save(shoppingCartItem);

        calculateShoppingCartAmount(shoppingCart);

        productEntry.setQuantity(productEntry.getQuantity() - quantity);
        productEntryRepository.save(productEntry);

        return shoppingCartRepository.save(shoppingCart);
    }

    public void purchase(int shoppingCartId, String deliveryLocation) {
        ShoppingCart shoppingCart = fetchShoppingCartById(shoppingCartId);
        calculateShoppingCartAmount(shoppingCart);

        if (shoppingCart.getItems().size() == 0) {
            throw new BusinessException("Shopping cart is empty!");
        } else if (shoppingCart.getOrder() != null) {
            throw new BusinessException("Order has already been made.");
        }

        String status = deliveryOrderService.getStatus(shoppingCart);
        int deliveryPrice = deliveryOrderService.getDeliveryPrice(shoppingCart);
        Date deliveryDate = deliveryOrderService.getDeliveryDate();

        Order order = digitalStoreService.createOrder(status, deliveryLocation, deliveryPrice, deliveryDate);
        shoppingCart.setOrder(order);
        shoppingCartRepository.save(shoppingCart);

        financialService.createPayment(shoppingCart.getAmount());
    }

    public boolean canAccessShoppingCart(int shoppingCartId) {
        Integer userId = authenticationService.getUserId();
        if (userId == null) {
            log.info("User ID is null.");
            return false;
        }

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (shoppingCart.isEmpty()) {
            log.info("Shopping cart was not found.");
            return false;
        }

        if (!userId.equals(shoppingCart.get().getCustomer().getId())) {
            log.info("Shopping cart does not belong to the logged in user.");
            return false;
        }

        return true;
    }

    private void calculateShoppingCartAmount(ShoppingCart shoppingCart) {
        float amount = shoppingCart.getItems().stream()
                .map(item -> item.getProductEntry().getPrice() * item.getQuantity())
                .reduce(0.0F, Float::sum);
        shoppingCart.setAmount(amount);
    }
}
