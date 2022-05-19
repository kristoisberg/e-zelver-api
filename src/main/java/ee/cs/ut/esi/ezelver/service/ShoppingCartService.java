package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.bpb.service.DeliveryOrderService;
import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.*;
import ee.cs.ut.esi.ezelver.repository.ProductEntryRepository;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartItemRepository;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
            return false;
        }

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if (shoppingCart.isEmpty()) {
            return false;
        }

        return userId.equals(shoppingCart.get().getCustomer().getId());
    }

    private void calculateShoppingCartAmount(ShoppingCart shoppingCart) {
        float amount = shoppingCart.getItems().stream()
                .map(item -> item.getProductEntry().getPrice() * item.getQuantity())
                .reduce(0.0F, Float::sum);
        shoppingCart.setAmount(amount);
    }
}
