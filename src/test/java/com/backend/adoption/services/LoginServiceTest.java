package com.backend.adoption.services;

import com.backend.adoption.dto.login.AuthRequest;
import com.backend.adoption.dto.login.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginService loginService;

    @Test
    void testLoginWithValidCredentials() {
        // Arrange
        AuthRequest request = new AuthRequest("email@email.com", "senha");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken("email@email.com", "senha");

        when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
        when(jwtService.generateToken("email@email.com")).thenReturn("fakeToken");

        // Act
        AuthResponse response = loginService.login(request);

        // Assert
        assertEquals("fakeToken", response.token());
    }
}
