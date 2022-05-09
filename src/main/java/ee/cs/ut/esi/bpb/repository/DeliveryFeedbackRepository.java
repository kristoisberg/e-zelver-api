package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryFeedbackRepository extends JpaRepository<DeliveryFeedback, Integer> {
    Optional<DeliveryFeedback> findById(int id);

    List<DeliveryFeedback> findAll();
}
