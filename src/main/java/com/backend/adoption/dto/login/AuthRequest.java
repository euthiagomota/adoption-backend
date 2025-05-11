package com.backend.adoption.dto.login;

public record AuthRequest(
        String email,
        String password
) { }
