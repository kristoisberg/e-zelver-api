package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
