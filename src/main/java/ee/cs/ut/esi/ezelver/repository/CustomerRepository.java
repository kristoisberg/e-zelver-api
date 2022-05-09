package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByName(String name);
    Optional<Customer> findById(int id);
    List<Customer> findAll();

}
