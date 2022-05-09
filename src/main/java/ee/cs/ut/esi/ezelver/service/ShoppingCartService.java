package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.bpb.service.DeliveryOrderService;
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
    private final WarehouseService warehouseService;
    private final AuthenticationService authenticationService;
    private final DeliveryOrderService deliveryOrderService;

    public ShoppingCart createShoppingCart() {
        Customer customer = customerService.fetchCustomerById(authenticationService.getCustomerId());
        ShoppingCart shoppingCart = new ShoppingCart(customer, 0);
        ShoppingCart result = shoppingCartRepository.save(shoppingCart);
        return result;
    }

    public ShoppingCart getShoppingCartById(int shoppingCartId) {
        ShoppingCart result = shoppingCartRepository.getById(shoppingCartId);
        return result;
    }

    public List<ShoppingCartItem> getShoppingCartItems(int shoppingCartId) {
        List<ShoppingCartItem> result = shoppingCartItemRepository.findByShoppingCartId(shoppingCartId);
        result.forEach(item -> item.setShoppingCart(null));
        return result;
    }

    public ShoppingCart addItem(int shoppingCartId, int productEntryId, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        ProductEntry productEntry = warehouseService.fetchProductById(productEntryId);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, productEntry, quantity);
        shoppingCartItemRepository.save(shoppingCartItem);

        //calculateShoppingCartAmount(shoppingCart);
        shoppingCart.getCustomer().setShoppingCarts(null);
        shoppingCart.getItems().forEach(item -> item.setShoppingCart(null));
        return shoppingCart;
    }

    public void purchase(int shoppingCartId, String deliveryLocation) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        //calculateShoppingCartAmount(shoppingCart);
        String status = deliveryOrderService.getStatus(shoppingCart);
        int deliveryPrice = deliveryOrderService.getDeliveryPrice(shoppingCart);
        digitalStoreService.createOrder(status, deliveryLocation, deliveryPrice, new Date());
        financialService.createPayment(shoppingCart.getAmount());
    }

    public boolean canAccessShoppingCart(int shoppingCartId) {
        Integer customerId = authenticationService.getCustomerId();
        if (customerId == null) {
            return false;
        }

        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        return customerId.equals(shoppingCart.getCustomer().getId());
    }

    /*private void calculateShoppingCartAmount(ShoppingCart shoppingCart) {
        int amount = shoppingCart.getItems().stream()
                .map(ShoppingCartItem::getProductEntry)
                .map(ProductEntry::getId) // todo: tegelikult peaks amount olema?
                .reduce(0, Integer::sum);
        shoppingCart.setAmount(amount);
    }*/
}
