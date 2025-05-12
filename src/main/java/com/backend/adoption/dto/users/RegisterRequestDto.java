package com.backend.adoption.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// VALIDATION
public record RegisterRequestDto(

        @Schema(description = "Nome completo", example = "João da Silva")
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @Schema(description = "E-mail válido", example = "joao@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,


        @Schema(description = "Senha com no mínimo 6 caracteres", example = "senha123")
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @Schema(description = "Confirmação da senha", example = "senha123")
        @NotBlank(message = "A confirmação de senha é obrigatória")
        String confirmPassword, // <--- campo necessário

        @Schema(description = "CPF válido", example = "808.607.974-11")
        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "^(\\d{3}\\.){2}\\d{3}-\\d{2}$", message = "CPF inválido")
        String cpf,

        @Schema(description = "Telefone com DDD", example = "(24) 0006-0725")
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone inválido")
        String phone
) { }
