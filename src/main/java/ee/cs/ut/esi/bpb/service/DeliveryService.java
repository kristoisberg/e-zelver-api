package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.Delivery;
import ee.cs.ut.esi.bpb.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepo;

    public Optional<Delivery> fetchDeliveryById(int id) {
        return deliveryRepo.findById(id);
    }

    public List<Delivery> fetchAllDeliveries() {
        return deliveryRepo.findAll();
    }

    public List<Delivery> fetchAllDeliveriesByDate(Date date) {
        return deliveryRepo.findAllByDeliveryDate(date);
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepo.save(delivery);
    }

    public Delivery updateDelivery(Delivery delivery, Date date, String address) {
        return delivery.updateDateAndAddress(date, address);
    }

    public Delivery updateDeliveryPickup(Delivery delivery, Date date, String address) {
        return delivery.updatePickupDateAndAddress(date, address);
    }

}
