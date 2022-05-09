package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryCustomerRepository extends JpaRepository<DeliveryCustomer, Integer> {
    Optional<DeliveryCustomer> findById(int id);

    List<DeliveryCustomer> findAll();
}
