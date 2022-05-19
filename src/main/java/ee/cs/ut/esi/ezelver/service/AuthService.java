package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.auth.jwt.JwtContext;
import ee.cs.ut.esi.ezelver.auth.jwt.JwtHandler;
import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtHandler jwtHandler;

    public Map<String, Object> login(String email, String password) {
        Optional<User> user = userService.fetchUserByEmail(email);

        if (user.isEmpty()) {
            throw new BusinessException("Account does not exist!");
        } else if (!new BCryptPasswordEncoder().matches(password, user.get().getPassword())) {
            throw new BusinessException("Password is incorrect!");
        }

        JwtContext jwtContext = jwtHandler.create(user.get().getId(), user.get().getName(), user.get().getRoles());
        return Map.of("jwt", jwtHandler.encode(jwtContext), "user", user);
    }

    public Map<String, Object> registerCustomer(String email, String password, String name, int age) {
        if (userService.fetchUserByEmail(email).isPresent()) {
            throw new BusinessException("Account name already exists!");
        }

        password = new BCryptPasswordEncoder().encode(password);
        Customer createdCustomer = userService.createCustomer(email, password, name, age);
        JwtContext jwtContext = jwtHandler.create(createdCustomer.getId(), createdCustomer.getName(), createdCustomer.getRoles());
        return Map.of("jwt", jwtHandler.encode(jwtContext), "user", createdCustomer);
    }

    public Map<String, Object> registerEmployee(String email, String password, String name, String position) {
        if (userService.fetchUserByEmail(email).isPresent()) {
            throw new BusinessException("Account name already exists!");
        }

        password = new BCryptPasswordEncoder().encode(password);
        Employee createdEmployee = userService.createEmployee(email, password, name, position);
        JwtContext jwtContext = jwtHandler.create(createdEmployee.getId(), createdEmployee.getName(), createdEmployee.getRoles());
        return Map.of("jwt", jwtHandler.encode(jwtContext), "user", createdEmployee);
    }

    public User getCurrentUser(Integer id) {
        return userService.fetchUserById(id).get();
    }
}
