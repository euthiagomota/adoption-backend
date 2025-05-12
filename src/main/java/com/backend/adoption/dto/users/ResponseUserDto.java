package com.backend.adoption.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
// VALIDATION
public record ResponseUserDto(
        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome completo", example = "João da Silva")
        String name,

        @Schema(description = "E-mail", example = "joao@email.com")
        String email,

        @Schema(description = "CPF", example = "808.607.974-11")
        String cpf,

        @Schema(description = "Telefone", example = "(24) 0006-0725")
        String phone,

        @Schema(description = "Data de criação", example = "2025-05-12T00:51:02.849Z", type = "string", format = "date-time")
        Date createdAt
) { }
