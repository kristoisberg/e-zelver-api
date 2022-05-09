package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import ee.cs.ut.esi.ezelver.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findById(int id);
    List<Store> findAll();
}
