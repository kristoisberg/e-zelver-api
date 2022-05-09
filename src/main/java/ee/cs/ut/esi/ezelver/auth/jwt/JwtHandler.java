package ee.cs.ut.esi.ezelver.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JwtHandler {
    private final String jwtSecret;
    private final int jwtLifetimeHours;

    public JwtHandler(@Value("${authentication.jwtSecret}") String jwtSecret,
                      @Value("${authentication.jwtLifetimeHours:1}") int jwtLifetimeHours) {
        this.jwtSecret = jwtSecret;
        this.jwtLifetimeHours = jwtLifetimeHours;
    }

    public JwtContext verify(String token) throws JwtException {
        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
        Claims claims = jwt.getBody();

        return new JwtContext(
                claims.get("id", Integer.class),
                claims.get("username", String.class),
                claims.get("roles", List.class),
                claims.getExpiration()
        );
    }

    public JwtContext create(Integer customerId, String name, String role) {
        return new JwtContext(
                customerId,
                name,
                Collections.singletonList(role),
                DateUtils.addHours(new Date(), jwtLifetimeHours)
        );
    }

    public String encode(JwtContext context) {
        return Jwts.builder()
                .setSubject(context.getName())
                .setExpiration(context.getExpiryTime())
                .setIssuedAt(new Date())
                .claim("id", context.getCustomerId())
                .claim("name", context.getName())
                .claim("roles", context.getRoles())
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
