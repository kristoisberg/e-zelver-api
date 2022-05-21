package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.exception.NotFoundException;
import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.repository.ProductEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<ProductEntry> product = productEntryRepository.findById(productId);
        if (product.isEmpty())
            throw new NotFoundException("Product not found with this ID.");
        return product.get();
    }

    public List<ProductEntry> fetchProductsByType(String productType) {
       return productEntryRepository.findByType(productType);
    }
    public ProductEntry addProduct(ProductEntry product) {
        return productEntryRepository.save(product);
    }
    public ProductEntry updateProduct(int id, ProductEntry product) {
        if (id != product.getId()) {
            throw new BusinessException("Invalid request.");
        }
        return productEntryRepository.save(product);
    }
}
