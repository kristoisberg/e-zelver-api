package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryInvoice;
import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.bpb.model.DeliveryPayment;
import ee.cs.ut.esi.bpb.repository.DeliveryInvoiceRepository;
import ee.cs.ut.esi.bpb.repository.DeliveryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryInvoiceService {

    @Autowired
    private DeliveryInvoiceRepository deliveryInvoiceRepo;

    public Optional<DeliveryInvoice> fetchInvoiceById(int id) {
        return deliveryInvoiceRepo.findById(id);
    }

    public List<DeliveryInvoice> fetchAllInvoices() {
        return deliveryInvoiceRepo.findAll();
    }

    public DeliveryInvoice createInvoice(DeliveryInvoice invoice) {
        return deliveryInvoiceRepo.save(invoice);
    }

}