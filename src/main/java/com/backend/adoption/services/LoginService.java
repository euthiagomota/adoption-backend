package com.backend.adoption.services;

import com.backend.adoption.dto.login.AuthRequest;
import com.backend.adoption.dto.login.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        authenticationManager.authenticate(authenticationToken);

        String token = jwtService.generateToken(request.email());

        return new AuthResponse(token);
    }
}
