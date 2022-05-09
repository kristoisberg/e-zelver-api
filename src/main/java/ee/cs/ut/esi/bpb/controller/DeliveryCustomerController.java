package ee.cs.ut.esi.bpb.controller;

import ee.cs.ut.esi.bpb.model.DeliveryCustomer;
import ee.cs.ut.esi.bpb.repository.DeliveryCustomerRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bpb/customer")
public class DeliveryCustomerController {

    @Autowired
    DeliveryCustomerRepository customerRepo;

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryCustomer> getCustomerById(@PathVariable("id") long id) {
        Optional<DeliveryCustomer> customer = customerRepo.findById(id);
        if (customer.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(customer.get());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DeliveryCustomer> getCustomerByEmail(@PathVariable("email") String email) {
        Optional<DeliveryCustomer> customer = customerRepo.findByEmail(email);
        if (customer.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(customer.get());
    }

    @GetMapping
    public ResponseEntity<List<DeliveryCustomer>> getAll() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<DeliveryCustomer> createCustomer(@Valid @RequestBody DeliveryCustomer customer, @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
    }

}
