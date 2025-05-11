package com.backend.adoption.filters;

import com.backend.adoption.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro para autenticação JWT em cada requisição.
 * Ele verifica a presença e a validade do token JWT, atualizando o contexto de segurança.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token != null && !token.isBlank()) {
            token = token.trim();

            if (token.startsWith("Bearer ")) {
                token = token.substring(7).trim();
            }

            if (!token.isBlank()) {
                try {
                    processToken(token);
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"status\":401,\"message\":\"Invalid token\"}");
                    return;
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":400,\"message\":\"Malformed or empty token\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void processToken(String token) {
        String username = jwtService.getUsernameFromToken(token);

        if (jwtService.isTokenValid(token, username)) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new IllegalArgumentException("Token is not valid.");
        }
    }
}
