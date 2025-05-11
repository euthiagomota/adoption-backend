package com.backend.adoption.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// VALIDATION
public record RegisterRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @NotBlank(message = "A confirmação de senha é obrigatória")
        String confirmPassword, // <--- campo necessário

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "^(\\d{3}\\.){2}\\d{3}-\\d{2}$", message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone inválido")
        String phone
) { }
