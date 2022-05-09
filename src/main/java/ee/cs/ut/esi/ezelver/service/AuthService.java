package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.auth.jwt.JwtContext;
import ee.cs.ut.esi.ezelver.auth.jwt.JwtHandler;
import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final JwtHandler jwtHandler;

    public String login(String name) {
        Customer customer = customerService.fetchCustomerByName(name);
        if (customer == null) {
            throw new BusinessException("Account does not exist!");
        }

        JwtContext jwtContext = jwtHandler.create(customer.getId(), customer.getName(), Role.CUSTOMER.name());
        return jwtHandler.encode(jwtContext);
    }

    public String register(String name, int age) {
        Customer customer = customerService.fetchCustomerByName(name);
        if (customer != null) {
            throw new BusinessException("Account name already exists!");
        }

        Customer createdCustomer = customerService.createCustomer(name, age);
        JwtContext jwtContext = jwtHandler.create(createdCustomer.getId(), createdCustomer.getName(), Role.CUSTOMER.name());
        return jwtHandler.encode(jwtContext);
    }

    public String loginEmployee(String name) {
        Employee employee = employeeService.fetchEmployeeByName(name);
        if (employee == null) {
            throw new BusinessException("Account does not exist!");
        }

        JwtContext jwtContext = jwtHandler.create(employee.getId(), employee.getName(), Role.EMPLOYEE.name());
        return jwtHandler.encode(jwtContext);
    }

    public String registerEmployee(String name, String position) {
        Employee customer = employeeService.fetchEmployeeByName(name);
        if (customer != null) {
            throw new BusinessException("Account name already exists!");
        }
        Employee createdEmployee = employeeService.createEmployee(name, position);
        JwtContext jwtContext = jwtHandler.create(createdEmployee.getId(), createdEmployee.getName(), Role.EMPLOYEE.name());
        return jwtHandler.encode(jwtContext);
    }
}
