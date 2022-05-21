package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.ProductEntry;
import ee.cs.ut.esi.ezelver.service.WarehouseService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final WarehouseService warehouseService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<ProductEntry>> getAllProducts() {
        return ResponseEntity.ok(warehouseService.fetchAllProducts());
    }

    @GetMapping("/available")
    public ResponseEntity<List<ProductEntry>> getAllAvailableProducts() {
        return ResponseEntity.ok(warehouseService.fetchAllAvailableProducts());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductEntry>> getAllAvailableProductsByType(@PathVariable String type) {
        return ResponseEntity.ok(warehouseService.fetchProductsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntry> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(warehouseService.fetchProductById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<ProductEntry> addProduct(@RequestBody @Valid ProductEntry product,
                                                   @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Incorrect product parameters!");

        return ResponseEntity.ok(warehouseService.addProduct(product));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<ProductEntry> updateProduct(@PathVariable int id,
                                                      @RequestBody @Valid ProductEntry product,
                                                      @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Incorrect product parameters!");

        return ResponseEntity.ok(warehouseService.updateProduct(id, product));
    }
}
