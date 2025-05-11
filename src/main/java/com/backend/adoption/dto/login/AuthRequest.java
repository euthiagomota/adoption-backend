package com.backend.adoption.dto.login;

// VALIDATION
public record AuthRequest(
        String email,
        String password
) { }
