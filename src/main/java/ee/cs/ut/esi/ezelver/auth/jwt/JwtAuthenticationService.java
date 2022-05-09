package ee.cs.ut.esi.ezelver.auth.jwt;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService implements AuthenticationService {
    @Override
    public Integer getCustomerId() {
        JwtContext jwtContext = JwtContextHolder.get();
        return jwtContext.getCustomerId();
    }

    @Override
    public String getName() {
        JwtContext jwtContext = JwtContextHolder.get();
        return jwtContext.getName();
    }
}
