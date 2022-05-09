package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Integer> {
    Optional<DeliveryHistory> findById(int id);

    List<DeliveryHistory> findAll();
}
