package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import ee.cs.ut.esi.ezelver.model.ShoppingCartItem;
import ee.cs.ut.esi.ezelver.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<ShoppingCart> getOrCreateShoppingCart() {
        return ResponseEntity.ok(shoppingCartService.getOrCreateShoppingCart());
    }

    @GetMapping("/{shoppingCartId}")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.fetchShoppingCartById(shoppingCartId));
    }

    @PutMapping("/{shoppingCartId}")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<?> purchaseShoppingCart(@PathVariable int shoppingCartId,
                                                  @RequestBody @Valid PurchaseRequestDto dto,
                                                  @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Delivery location is not specified.");

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
                                                @RequestBody @Valid ShoppingCartItem shoppingCartItem,
                                                @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Shopping cart item has wrong parameters.");

        return ResponseEntity.ok(shoppingCartService.addItem(
                shoppingCartId, shoppingCartItem.getProductEntry().getId(), shoppingCartItem.getQuantity()));
    }

    @DeleteMapping("/{shoppingCartId}/items/{cartItemId}")
    @PreAuthorize("@shoppingCartService.canAccessShoppingCart(#shoppingCartId)")
    public ResponseEntity<ShoppingCart> removeItem(@PathVariable int shoppingCartId,
                                                   @PathVariable int cartItemId) {
        shoppingCartService.deleteShoppingCartItem(
                shoppingCartId, cartItemId);
        return ResponseEntity.ok(null);
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class PurchaseRequestDto {
        @NotNull
        private String deliveryLocation;
    }
}
