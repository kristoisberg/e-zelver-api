package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import ee.cs.ut.esi.ezelver.model.ShoppingCartItem;
import ee.cs.ut.esi.ezelver.service.ShoppingCartService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<ShoppingCart> createShoppingCart() {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart());
    }

    @GetMapping("/{shoppingCartId}")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartById(shoppingCartId));
    }

    @PutMapping("/{shoppingCartId}")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<?> purchaseShoppingCart(@PathVariable int shoppingCartId,
                                                  @RequestBody PurchaseRequestDto dto) {
        shoppingCartService.purchase(shoppingCartId, dto.getDeliveryLocation());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{shoppingCartId}/items")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<List<ShoppingCartItem>> getShoppingCartItems(@PathVariable int shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartItems(shoppingCartId));
    }

    @PutMapping("/{shoppingCartId}/items")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<ShoppingCart> addItem(@PathVariable int shoppingCartId,
                                                @RequestBody ShoppingCartItem shoppingCartItem) {
        return ResponseEntity.ok(shoppingCartService.addItem(
                shoppingCartId, shoppingCartItem.getProductEntryId(), shoppingCartItem.getQuantity()));
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class PurchaseRequestDto {
        private String deliveryLocation;
    }
}
