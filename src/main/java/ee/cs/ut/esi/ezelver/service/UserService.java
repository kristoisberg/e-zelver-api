package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.model.User;
import ee.cs.ut.esi.ezelver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> fetchUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Customer> fetchCustomerById(int id) {
        return userRepository.findCustomerById(id);
    }

    public Optional<Employee> fetchEmployeeById(int id) {
        return userRepository.findEmployeeById(id);
    }

    public Customer createCustomer(String email, String password, String name, int age) {
        Customer customer = new Customer(email, password, name, age);
        return userRepository.save(customer);
    }

    public Employee createEmployee(String email, String password, String name, String position) {
        Employee employee = new Employee(email, password, name, position);
        return userRepository.save(employee);
    }
}
