package ee.cs.ut.esi.ezelver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
