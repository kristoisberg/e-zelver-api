package ee.cs.ut.esi.bpb.repository;

import ee.cs.ut.esi.bpb.model.DeliveryInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInvoiceRepository extends JpaRepository<DeliveryInvoice, Long> {
}
