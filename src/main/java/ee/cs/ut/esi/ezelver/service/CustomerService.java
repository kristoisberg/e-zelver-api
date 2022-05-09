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

    public Customer fetchCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer createCustomer(String name, int age) {
        Customer customer = new Customer(name, age);
        return customerRepository.save(customer);
    }
}
