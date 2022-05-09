package ee.cs.ut.esi.ezelver.service;

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
    private final DigitalStoreService digitalStoreService;
    private final FinancialService financialService;

    public ShoppingCart createShoppingCart(int customerId) {
        ShoppingCart shoppingCart = new ShoppingCart(customerId, 0);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartById(int shoppingCartId) {
        return shoppingCartRepository.getById(shoppingCartId);
    }

    public List<ShoppingCartItem> getShoppingCartItems(int shoppingCartId) {
        return shoppingCartItemRepository.findByShoppingCartId(shoppingCartId);
    }

    public ShoppingCart addItem(int shoppingCartId, int productEntryId, int quantity) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCartId, productEntryId, quantity);
        ShoppingCartItem result = shoppingCartItemRepository.save(shoppingCartItem);

        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        shoppingCart.getItems().add(result);
        calculateShoppingCartAmount(shoppingCart);
        return shoppingCart;
    }

    public void purchase(int shoppingCartId, String deliveryLocation) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        calculateShoppingCartAmount(shoppingCart);
        // todo: BPB stuff
        digitalStoreService.createOrder("BPB", deliveryLocation, 420, new Date()); // todo status, price, date
        financialService.createPayment(shoppingCart.getAmount());
    }

    private void calculateShoppingCartAmount(ShoppingCart shoppingCart) {
        int amount = shoppingCart.getItems().stream()
                .map(ShoppingCartItem::getProductEntry)
                .map(ProductEntry::getId) // todo: tegelikult peaks amount olema?
                .reduce(0, Integer::sum);
        shoppingCart.setAmount(amount);
    }
}
