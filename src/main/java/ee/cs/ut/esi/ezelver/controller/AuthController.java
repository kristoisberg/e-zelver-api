package ee.cs.ut.esi.ezelver.controller;

import ee.cs.ut.esi.ezelver.service.AuthService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/customers/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.login(request.getName())));
    }

    @PostMapping("/api/customers/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.register(request.getName(), request.getAge())));
    }

    @PostMapping("/api/employees/login")
    public ResponseEntity<AuthResponseDto> loginEmployee(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.loginEmployee(request.getName())));
    }

    @PostMapping("/api/employees/register")
    public ResponseEntity<AuthResponseDto> registerEmployee(@RequestBody RegisterEmployeeRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.registerEmployee(request.getName(), request.getPosition())));
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class LoginRequestDto {
        private String name;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class RegisterRequestDto {
        private String name;
        private int age;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class RegisterEmployeeRequestDto {
        private String name;
        private String position;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class AuthResponseDto {
        private String jwt;
    }
}
