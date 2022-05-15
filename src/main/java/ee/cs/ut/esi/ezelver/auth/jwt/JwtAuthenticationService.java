package ee.cs.ut.esi.ezelver.auth.jwt;

import ee.cs.ut.esi.ezelver.auth.AuthenticationService;
import ee.cs.ut.esi.ezelver.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService implements AuthenticationService {
    @Override
    public Integer getUserId() {
        JwtContext jwtContext = JwtContextHolder.get();

        if (jwtContext == null)
            throw new NotFoundException("Invalid user");

        return jwtContext.getUserId();
    }

    @Override
    public String getName() {
        JwtContext jwtContext = JwtContextHolder.get();

        if (jwtContext == null)
            throw new NotFoundException("Invalid user");

        return jwtContext.getName();
    }
}
