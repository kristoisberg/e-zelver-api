package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.model.Order;
import ee.cs.ut.esi.ezelver.service.DigitalStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final DigitalStoreService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<List<Order>> getAllCustomerOrders() {
        return ResponseEntity.ok(orderService.fetchAllCustomerOrders());
    }

}
