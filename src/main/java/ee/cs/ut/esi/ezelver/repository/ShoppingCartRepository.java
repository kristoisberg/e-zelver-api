package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findById(int id);

}
