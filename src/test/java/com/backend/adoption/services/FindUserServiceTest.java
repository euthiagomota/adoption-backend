package com.backend.adoption.services;

import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.entities.User;
import com.backend.adoption.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private FindUserService findUserService;

    public FindUserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should successfully find the user and return ResponseUserDto")
    void execute_ShouldReturnResponseUserDto_WhenUserExists() {
        String token = "Bearer valid.token.value";
        String email = "test@example.com";
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail(email);
        user.setCpf("123.456.789-10");
        user.setPhone("(11) 1234-5678");
        user.setCreatedAt(new Date());

        when(jwtService.getUsernameFromToken("valid.token.value")).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        ResponseUserDto response = findUserService.execute(token);

        assertNotNull(response);
        assertEquals(user.getId(), response.id());
        assertEquals(user.getName(), response.name());
        assertEquals(user.getEmail(), response.email());
        assertEquals(user.getCpf(), response.cpf());
        assertEquals(user.getPhone(), response.phone());
        assertEquals(user.getCreatedAt(), response.createdAt());

        verify(jwtService, times(1)).getUsernameFromToken("valid.token.value");
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should throw UsernameNotFoundException when user is not found")
    void execute_ShouldThrowException_WhenUserDoesNotExist() {
        String token = "Bearer another.valid.token";
        String email = "nonexistent@example.com";

        when(jwtService.getUsernameFromToken("another.valid.token")).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            findUserService.execute(token);
        });

        assertEquals("User with this email was not found: " + email, exception.getMessage());

        verify(jwtService, times(1)).getUsernameFromToken("another.valid.token");
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should handle token without 'Bearer ' prefix")
    void execute_ShouldHandleTokenWithoutBearerPrefix() {
        String token = "valid.token.value";
        String email = "user@example.com";
        User user = new User();
        user.setId(2L);
        user.setName("User Name");
        user.setEmail(email);
        user.setCpf("987.654.321-00");
        user.setPhone("(22) 4444-5555");
        user.setCreatedAt(new Date());

        when(jwtService.getUsernameFromToken(token)).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        ResponseUserDto response = findUserService.execute(token);

        assertNotNull(response);
        assertEquals(user.getId(), response.id());
        assertEquals(user.getName(), response.name());
        assertEquals(user.getEmail(), response.email());
        assertEquals(user.getCpf(), response.cpf());
        assertEquals(user.getPhone(), response.phone());
        assertEquals(user.getCreatedAt(), response.createdAt());

        verify(jwtService, times(1)).getUsernameFromToken(token);
        verify(userRepository, times(1)).findByEmail(email);
    }
}