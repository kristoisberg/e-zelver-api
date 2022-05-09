package ee.cs.ut.esi.ezelver.repository;

import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByName(String name);
    Optional<Employee> findById(int id);
    List<Employee> findAll();
}
