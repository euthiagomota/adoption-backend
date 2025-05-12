package com.backend.adoption.controllers;

import com.backend.adoption.dto.login.AuthRequest;
import com.backend.adoption.dto.login.AuthResponse;
import com.backend.adoption.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.adoption.dto.users.RegisterRequestDto;
import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro")
public class AuthController {
    
    @Autowired
    AuthService authService;
    @Autowired
    LoginService loginService;

    @PostMapping("/register")
    @Operation(
            summary = "Registrar um novo usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para criar um usuário",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RegisterRequestDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseUserDto.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    public ResponseEntity<ResponseUserDto> register(@Valid @RequestBody RegisterRequestDto req) {
        ResponseUserDto response = this.authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Autenticar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AuthRequest.class),
                            examples = @ExampleObject(value = "{\n  \"email\": \"usuario@email.com\",\n  \"password\": \"senha123\"\n}")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class)))
            }
    )
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        AuthResponse token = this.loginService.login(req);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    
}
