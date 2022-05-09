package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, Integer> {
    public List<ProductEntry> findByAvailableTrue();
    public List<ProductEntry> findByType(String type);
}
