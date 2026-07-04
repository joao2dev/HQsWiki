package joao2dev.ProjetoHq.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {

        String token = extrairTokenDoCookie(request);

        if (token == null) {

            String authHeader = request.getHeader("Authorization");
            if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }

        if (token != null) {
            Optional<JWTUserData> optionalUserData = tokenService.verifyToken(token);
            if (optionalUserData.isPresent()) {
                JWTUserData userData = optionalUserData.get();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userData, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extrairTokenDoCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}