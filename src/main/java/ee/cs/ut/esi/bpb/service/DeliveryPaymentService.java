package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryCustomer;
import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.bpb.model.DeliveryPayment;
import ee.cs.ut.esi.bpb.repository.DeliveryOrderRepository;
import ee.cs.ut.esi.bpb.repository.DeliveryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPaymentService {

    @Autowired
    private DeliveryPaymentRepository deliveryPaymentRepo;

    public Optional<DeliveryPayment> fetchPaymentById(int id) {
        return deliveryPaymentRepo.findById(id);
    }

    public List<DeliveryPayment> fetchAllPayments() {
        return deliveryPaymentRepo.findAll();
    }

    public DeliveryPayment createPayment(DeliveryPayment payment) {
        return deliveryPaymentRepo.save(payment);
    }

}