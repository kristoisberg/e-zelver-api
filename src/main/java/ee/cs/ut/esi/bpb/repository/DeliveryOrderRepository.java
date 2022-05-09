package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Integer> {
    Optional<DeliveryOrder> findById(int id);

    List<DeliveryOrder> findAll();
}
