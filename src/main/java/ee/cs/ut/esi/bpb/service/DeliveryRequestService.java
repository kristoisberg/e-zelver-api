package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryFeedback;
import ee.cs.ut.esi.bpb.model.DeliveryRequest;
import ee.cs.ut.esi.bpb.repository.DeliveryFeedbackRepository;
import ee.cs.ut.esi.bpb.repository.DeliveryRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRequestService {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepo;

    public Optional<DeliveryRequest> fetchRequestById(int id) {
        return deliveryRequestRepo.findById(id);
    }

    public List<DeliveryRequest> fetchAllRequests() {
        return deliveryRequestRepo.findAll();
    }

    public DeliveryRequest createRequest(DeliveryRequest request) {
        return deliveryRequestRepo.save(request);
    }

}
