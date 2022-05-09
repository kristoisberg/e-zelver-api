package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Optional<Delivery> findById(int id);

    List<Delivery> findAll();

    List<Delivery> findAllByDate(Date date);

}
