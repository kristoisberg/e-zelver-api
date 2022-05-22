package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.model.Order;
import ee.cs.ut.esi.ezelver.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitalStoreService {
    private final OrderRepository orderRepository;
    private final AuthenticationService authenticationService;

    public Order fetchOrderById(int orderId) {
        return orderRepository.getById(orderId);
    }

    public List<Order> fetchAllCustomerOrders() {
        return orderRepository.findAllCustomerOrders(authenticationService.getUserId());
    }

    public Order createOrder(String status, String deliveryLocation, int deliveryPrice, Date deliveryTime) {
        Order order = new Order(status, deliveryLocation, deliveryPrice, deliveryTime, null);
        return orderRepository.save(order);
    }
}
