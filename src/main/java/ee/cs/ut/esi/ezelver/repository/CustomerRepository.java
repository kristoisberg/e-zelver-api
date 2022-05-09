package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.bpb.model.DeliveryFeedback;
import ee.cs.ut.esi.ezelver.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(int id);
}
