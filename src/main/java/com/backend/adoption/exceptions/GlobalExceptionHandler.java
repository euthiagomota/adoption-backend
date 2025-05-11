package com.backend.adoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, Object> errorResponse = Map.of(
                "statusCode", HttpStatus.UNAUTHORIZED.value(),
                "message", "Invalid credentials. Please check your email and password.",
                "error", "Unauthorized",
                "details", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        Map<String, Object> errorResponse = Map.of(
                "statusCode", HttpStatus.NOT_FOUND.value(),
                "message", "User not found with the provided email.",
                "error", "User Not Found",
                "details", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Object> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
        Map<String, Object> errorResponse = Map.of(
                "statusCode", HttpStatus.UNAUTHORIZED.value(),
                "message", "Authentication credentials are missing or invalid.",
                "error", "Unauthorized",
                "details", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> errorResponse = Map.of(
                "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "message", "An unexpected error occurred.",
                "error", "Internal Server Error",
                "details", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
