package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Integer> {
    Optional<DeliveryRequest> findById(int id);

    List<DeliveryRequest> findAll();
}
