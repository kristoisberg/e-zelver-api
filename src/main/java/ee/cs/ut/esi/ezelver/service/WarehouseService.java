package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.repository.ProductEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final ProductEntryRepository productEntryRepository;

    public List<ProductEntry> fetchAllProducts() {
        return productEntryRepository.findAll();
    }

    public List<ProductEntry> fetchAllAvailableProducts() {
        return productEntryRepository.findByQuantityGreaterThan(0);
    }

    public ProductEntry fetchProductById(int productId) {
        return productEntryRepository.getById(productId);
    }

    public List<ProductEntry> fetchProductsByType(String productType) {
       return productEntryRepository.findByType(productType);
    }
}
