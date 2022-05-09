package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<ProductEntry>> fetchAllProducts() {
        return ResponseEntity.ok(warehouseService.fetchAllProducts());
    }

    @GetMapping("/available")
    public ResponseEntity<List<ProductEntry>> fetchAllAvailableProducts() {
        return ResponseEntity.ok(warehouseService.fetchAllAvailableProducts());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductEntry>> fetchAllAvailableProducts(String type) {
        return ResponseEntity.ok(warehouseService.fetchProductsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntry> fetchAllAvailableProducts(int id) {
        return ResponseEntity.ok(warehouseService.fetchProductById(id));
    }
}
