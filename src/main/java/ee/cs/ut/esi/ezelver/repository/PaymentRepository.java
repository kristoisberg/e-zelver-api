package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Order;
import ee.cs.ut.esi.ezelver.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findById(int id);

}
