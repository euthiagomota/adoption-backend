package com.backend.adoption.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// VALIDATION
public record AuthRequest(
        @Schema(description = "E-mail do usuário", example = "usuario@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @Schema(description = "Senha do usuário", example = "senha123")
        @NotBlank(message = "A senha é obrigatória")
        String password
) { }
