package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {
    List<ShoppingCartItem> findByShoppingCartId(int shoppingCartId);
}
