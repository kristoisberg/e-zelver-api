package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ProductEntry>> fetchAllAvailableProducts(@PathVariable String type) {
        return ResponseEntity.ok(warehouseService.fetchProductsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntry> fetchAllAvailableProducts(@PathVariable int id) {
        return ResponseEntity.ok(warehouseService.fetchProductById(id));
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<ProductEntry> addProduct(@RequestBody ProductEntry product) {
        return ResponseEntity.ok(warehouseService.addProduct(product));
    }
}
