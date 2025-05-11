package com.backend.adoption.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(MethodArgumentNotValidException .class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (msg1, msg2) -> msg1 // em caso de campos duplicados
                        )
                );

        Map<String, Object> response = Map.of(
                "statusCode", HttpStatus.BAD_REQUEST.value(),
                "message", "Validation failed",
                "errors", errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
