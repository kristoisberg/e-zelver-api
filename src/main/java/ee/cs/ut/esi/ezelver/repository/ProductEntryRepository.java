package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, Integer> {
    Optional<ProductEntry> findById(Integer integer);

    List<ProductEntry> findAll();

    List<ProductEntry> findByQuantityGreaterThan(int quantity);

    List<ProductEntry> findByType(String type);
}
