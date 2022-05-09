package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer fetchCustomerById(int customerId) {
        return customerRepository.getById(customerId);
    }
}
