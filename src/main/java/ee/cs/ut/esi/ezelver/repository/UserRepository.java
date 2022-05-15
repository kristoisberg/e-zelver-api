package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    @Query("from Customer where id = ?1")
    Optional<Customer> findCustomerById(int id);

    @Query("from Employee where id = ?1")
    Optional<Employee> findEmployeeById(int id);
}
