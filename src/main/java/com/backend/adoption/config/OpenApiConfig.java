package com.backend.adoption.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pet Adoption API",
                version = "1.0",
                description = "API para adoção de pets"
        ),
        security = @SecurityRequirement(name = "jwtAuth") // Aplica globalmente
)
@SecurityScheme(
        name = "jwtAuth", // Nome do esquema de segurança
        type = SecuritySchemeType.HTTP, // Tipo: HTTP
        scheme = "bearer", // Tipo de autenticação
        bearerFormat = "JWT", // Formato: JWT
        description = "Informe aqui o Token JWT" // Descrição adicional
)
public class OpenApiConfig {
    // Nenhum código necessário aqui, apenas as anotações são suficientes
}
