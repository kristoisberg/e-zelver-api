package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);

    List<Order> findAll();

    @Query("select o from Order o where o.shoppingCart.customer.id = ?1")
    List<Order> findAllCustomerOrders(int userId);
}
