package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryPaymentRepository extends JpaRepository<DeliveryPayment, Integer> {
    Optional<DeliveryPayment> findById(int id);

    List<DeliveryPayment> findAll();
}
