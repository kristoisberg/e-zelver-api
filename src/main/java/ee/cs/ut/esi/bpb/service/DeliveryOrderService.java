package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.bpb.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderService {

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepo;

    public Optional<DeliveryOrder> fetchOrderById(int id) {
        return deliveryOrderRepo.findById(id);
    }

    public List<DeliveryOrder> fetchAllOrders() {
        return deliveryOrderRepo.findAll();
    }

}
