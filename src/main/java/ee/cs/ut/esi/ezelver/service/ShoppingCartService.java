package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import ee.cs.ut.esi.ezelver.model.ShoppingCartItem;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartItemRepository;
import ee.cs.ut.esi.ezelver.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final CustomerService customerService;
    private final DigitalStoreService digitalStoreService;
    private final FinancialService financialService;
    private final AuthenticationService authenticationService;

    public ShoppingCart createShoppingCart() {
        Customer customer = customerService.fetchCustomerById(authenticationService.getCustomerId());
        ShoppingCart shoppingCart = new ShoppingCart(customer, 0);
        ShoppingCart result = shoppingCartRepository.save(shoppingCart);
        result.getCustomer().setShoppingCarts(null);
        result.getItems().forEach(item -> item.setShoppingCart(null));
        return result;
    }

    public ShoppingCart getShoppingCartById(int shoppingCartId) {
        ShoppingCart result = shoppingCartRepository.getById(shoppingCartId);
        result.getCustomer().setShoppingCarts(null);
        result.getItems().forEach(item -> item.setShoppingCart(null));
        return result;
    }

    public List<ShoppingCartItem> getShoppingCartItems(int shoppingCartId) {
        List<ShoppingCartItem> result = shoppingCartItemRepository.findByShoppingCartId(shoppingCartId);
        result.forEach(item -> item.setShoppingCart(null));
        return result;
    }

    public ShoppingCart addItem(int shoppingCartId, int productEntryId, int quantity) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCartId, productEntryId, quantity);
        ShoppingCartItem result = shoppingCartItemRepository.save(shoppingCartItem);

        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        shoppingCart.getItems().add(result);
        calculateShoppingCartAmount(shoppingCart);
        shoppingCart.getCustomer().setShoppingCarts(null);
        shoppingCart.getItems().forEach(item -> item.setShoppingCart(null));
        return shoppingCart;
    }

    public void purchase(int shoppingCartId, String deliveryLocation) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        calculateShoppingCartAmount(shoppingCart);
        // todo: BPB stuff
        digitalStoreService.createOrder("BPB", deliveryLocation, 420, new Date()); // todo status, price, date
        financialService.createPayment(shoppingCart.getAmount());
    }

    public boolean canAccessShoppingCart(int shoppingCartId) {
        Integer customerId = authenticationService.getCustomerId();
        if (customerId == null) {
            return false;
        }

        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        return customerId.equals(shoppingCart.getCustomerId());
    }

    private void calculateShoppingCartAmount(ShoppingCart shoppingCart) {
        int amount = shoppingCart.getItems().stream()
                .map(ShoppingCartItem::getProductEntry)
                .map(ProductEntry::getId) // todo: tegelikult peaks amount olema?
                .reduce(0, Integer::sum);
        shoppingCart.setAmount(amount);
    }
}
