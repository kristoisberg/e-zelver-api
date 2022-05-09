package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
