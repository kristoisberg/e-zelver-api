package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.exception.BusinessException;
import ee.cs.ut.esi.ezelver.model.Customer;
import ee.cs.ut.esi.ezelver.model.Employee;
import ee.cs.ut.esi.ezelver.model.User;
import ee.cs.ut.esi.ezelver.service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationService authenticationService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto request, @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Request body needs to have email and password!");

        return ResponseEntity.ok(Map.of("jwt", authService.login(request.getEmail(), request.getPassword())));
    }

    @PostMapping("/api/auth/customers/register")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid Customer customer, @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Incorrect customer parameters!");

        return ResponseEntity.ok(Map.of("jwt", authService.registerCustomer(customer.getEmail(), customer.getPassword(), customer.getName(), customer.getAge())));
    }

    @PostMapping("/api/auth/employees/register")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid Employee employee, @Parameter(hidden = true) BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BusinessException("Incorrect employee parameters!");

        return ResponseEntity.ok(Map.of("jwt", authService.registerEmployee(employee.getEmail(), employee.getPassword(), employee.getName(), employee.getPosition())));
    }

    @GetMapping("/api/auth/current")
    public ResponseEntity<User> currentUser() {
        return ResponseEntity.ok(authService.getCurrentUser(authenticationService.getUserId()));
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class LoginRequestDto {
        @NotNull
        private String email;
        @NotNull
        private String password;
    }
}
