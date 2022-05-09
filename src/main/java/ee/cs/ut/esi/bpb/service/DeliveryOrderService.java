package ee.cs.ut.esi.bpb.service;

import ee.cs.ut.esi.bpb.model.DeliveryInvoice;
import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.bpb.model.DeliveryPayment;
import ee.cs.ut.esi.bpb.repository.DeliveryOrderRepository;
import ee.cs.ut.esi.ezelver.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderService {

    private static String ACCEPTED = "accepted";
    private static String REVIEW_NEEDED = "review needed";

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepo;
    @Autowired
    private DeliveryInvoiceService deliveryInvoiceService;

    public Optional<DeliveryOrder> fetchOrderById(int id) {
        return deliveryOrderRepo.findById(id);
    }

    public List<DeliveryOrder> fetchAllOrders() {
        return deliveryOrderRepo.findAll();
    }

    public DeliveryOrder createOrder(DeliveryOrder order) {
        return deliveryOrderRepo.save(order);
    }

    public String getStatus(ShoppingCart shoppingCart) {
        if (shoppingCart.getItems().size() > 0)  {
            return ACCEPTED;
        }
        else {
            return REVIEW_NEEDED;
        }
    }
    public int getDeliveryPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().size();
    }


}
