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

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.login(request.getName())));
    }

    @PostMapping("/api/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(new AuthResponseDto(authService.register(request.getName(), request.getAge())));
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
    @AllArgsConstructor
    @Getter
    @Setter
    static class AuthResponseDto {
        private String jwt;
    }
}
