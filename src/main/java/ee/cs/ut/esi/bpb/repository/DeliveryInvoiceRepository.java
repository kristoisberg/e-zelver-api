package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryInvoiceRepository extends JpaRepository<DeliveryInvoice, Integer> {
    Optional<DeliveryInvoice> findById(int id);

    List<DeliveryInvoice> findAll();
}
