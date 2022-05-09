package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.repository.CustomerRepository;
import ee.cs.ut.esi.ezelver.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee fetchEmployeeById(int employeeId) {
        return employeeRepository.getById(employeeId);
    }

    public Employee fetchEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee createEmployee(String name, String position) {
        Employee employee = new Employee(name, position);
        return employeeRepository.save(employee);
    }
}
