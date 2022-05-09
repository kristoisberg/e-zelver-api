package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
