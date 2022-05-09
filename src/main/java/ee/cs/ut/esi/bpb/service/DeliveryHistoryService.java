package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.Delivery;
import ee.cs.ut.esi.bpb.model.DeliveryHistory;
import ee.cs.ut.esi.bpb.repository.DeliveryHistoryRepository;
import ee.cs.ut.esi.bpb.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryHistoryService {

    @Autowired
    private DeliveryHistoryRepository deliveryHistoryRepo;

    public Optional<DeliveryHistory> fetchDeliveryHistoryById(int id) {
        return deliveryHistoryRepo.findById(id);
    }

    public List<DeliveryHistory> fetchAllDeliveryHistories() {
        return deliveryHistoryRepo.findAll();
    }

    public DeliveryHistory createDeliveryHistory(DeliveryHistory delivery) {
        return deliveryHistoryRepo.save(delivery);
    }

}
