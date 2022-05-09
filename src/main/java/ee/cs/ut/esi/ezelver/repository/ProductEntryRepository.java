package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, Integer> {
    List<ProductEntry> findByQuantityGreaterThan(int quantity);
    List<ProductEntry> findByType(String type);
}
