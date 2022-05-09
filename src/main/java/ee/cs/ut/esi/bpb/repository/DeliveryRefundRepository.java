package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryRefund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRefundRepository extends JpaRepository<DeliveryRefund, Integer> {
    Optional<DeliveryRefund> findById(int id);

    Optional<DeliveryRefund> findByOrderId(int id);

    List<DeliveryRefund> findAll();
}
