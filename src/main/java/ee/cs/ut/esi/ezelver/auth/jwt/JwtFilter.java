package ee.cs.ut.esi.ezelver.auth.jwt;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private final JwtHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);

        if (authorizationHeader != null && authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            String token = authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX.length());

            try {
                JwtContext context = jwtHandler.verify(token);
                Authentication authentication = getAuthentication(context, token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                JwtContextHolder.set(context);
            } catch (JwtException ignored) {}
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
            JwtContextHolder.clear();
        }
    }

    private Authentication getAuthentication(JwtContext context, String token) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                context.getCustomerId(),
                null,
                context.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList())
        );
        authenticationToken.setDetails(token);
        return authenticationToken;
    }
}
