package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.bpb.model.DeliveryRefund;
import ee.cs.ut.esi.bpb.repository.DeliveryRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRefundService {

    @Autowired
    private DeliveryRefundRepository refundRepo;

    @Autowired
    private DeliveryOrderService orderService;

    public Optional<DeliveryRefund> fetchRefundById(int id) {
        return refundRepo.findById(id);
    }

    public Optional<DeliveryRefund> fetchRefundByOrderId(int id) {
        return refundRepo.findByOrderId(id);
    }


    public List<DeliveryRefund> fetchAllRefunds() {
        return refundRepo.findAll();
    }

    public DeliveryRefund cancelRefund(DeliveryRefund refund) {
        return refund.setCancelled(true);
    }

    public DeliveryOrder approveRefund(DeliveryRefund refund) {
        Optional<DeliveryOrder> order = orderService.fetchOrderById(refund.getOrderId());
        if (order.isEmpty())
            return null;
        return order.get().setRefunded(true);
    }

}
