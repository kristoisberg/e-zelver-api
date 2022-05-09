package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryCustomerRepository extends JpaRepository<DeliveryCustomer, Long> {
    Optional<DeliveryCustomer> findById(long id);
    Optional<DeliveryCustomer> findByEmail(String email);
    List<DeliveryCustomer> findAll();
}
