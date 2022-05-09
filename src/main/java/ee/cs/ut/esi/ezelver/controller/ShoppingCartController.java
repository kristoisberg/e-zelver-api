package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import ee.cs.ut.esi.ezelver.model.ShoppingCartItem;
import ee.cs.ut.esi.ezelver.service.ShoppingCartService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart() {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart(0)); // todo
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartById(shoppingCartId));
    }

    @PutMapping("/{shoppingCartId}")
    public ResponseEntity<?> purchaseShoppingCart(@PathVariable int shoppingCartId,
                                                  @RequestBody PurchaseShoppingCartDto dto) {
        shoppingCartService.purchase(shoppingCartId, dto.getDeliveryLocation());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{shoppingCartId}/items")
    public ResponseEntity<List<ShoppingCartItem>> getShoppingCartItems(@PathVariable int shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartItems(shoppingCartId));
    }

    @PutMapping("/{shoppingCartId}/items")
    public ResponseEntity<ShoppingCart> addItem(@PathVariable int shoppingCartId,
                                                @RequestBody ShoppingCartItem shoppingCartItem) {
        return ResponseEntity.ok(shoppingCartService.addItem(
                shoppingCartId, shoppingCartItem.getProductEntryId(), shoppingCartItem.getQuantity()));
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class PurchaseShoppingCartDto {
        private String deliveryLocation;
    }
}
