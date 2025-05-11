package com.backend.adoption.dto.users;

// VALIDATION
public record RegisterRequestDto(
    String name,
    String email,
    String password,
    String confirmPassword,
    String cpf,
    String phone
) { }
