package com.backend.adoption.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF (útil para APIs REST)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/auth/register").permitAll() // Libera o endpoint de registro
                                .anyRequest().authenticated() // O restante precisa estar autenticado
                )
                .httpBasic(basic -> basic.disable()) // Desativa autenticação básica
                .formLogin(login -> login.disable()); // Desativa o formulário padrão de login

        return http.build();
    }
}
