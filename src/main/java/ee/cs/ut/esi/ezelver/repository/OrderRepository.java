package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.bpb.model.DeliveryOrder;
import ee.cs.ut.esi.ezelver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);
    List<Order> findAll();
}
