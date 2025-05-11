package com.backend.adoption.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// VALIDATION
public record AuthRequest(
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String password
) { }
