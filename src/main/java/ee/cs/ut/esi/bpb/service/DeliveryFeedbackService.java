package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryFeedback;
import ee.cs.ut.esi.bpb.model.DeliveryInvoice;
import ee.cs.ut.esi.bpb.repository.DeliveryFeedbackRepository;
import ee.cs.ut.esi.bpb.repository.DeliveryInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryFeedbackService {

    @Autowired
    private DeliveryFeedbackRepository deliveryFeedbackRepo;

    public Optional<DeliveryFeedback> fetchFeedbackById(int id) {
        return deliveryFeedbackRepo.findById(id);
    }

    public List<DeliveryFeedback> fetchAllFeedbacks() {
        return deliveryFeedbackRepo.findAll();
    }

    public DeliveryFeedback createFeedback(DeliveryFeedback feedback) {
        return deliveryFeedbackRepo.save(feedback);
    }

}