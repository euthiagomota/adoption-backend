package com.backend.adoption.controllers;

import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.services.FindUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    FindUserService findUserService;

@GetMapping("/me")
@Operation(summary = "Buscar usuário autenticado",
        description = "Retorna os dados do usuário autenticado com base no token JWT fornecido no cabeçalho Authorization.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Token inválido ou ausente")
})
    public ResponseEntity<ResponseUserDto> findUser(
            @Parameter(description = "Token JWT de autenticação", required = true)
            @RequestHeader("Authorization") String authHeader) {
        ResponseUserDto response = this.findUserService.execute(authHeader);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
