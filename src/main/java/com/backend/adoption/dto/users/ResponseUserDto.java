package com.backend.adoption.dto.users;

import java.util.Date;

public record ResponseUserDto(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        Date createdAt
) { }
