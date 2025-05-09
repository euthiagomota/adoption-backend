package com.backend.adoption.controllers;

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
public class AuthController {
    
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> register(@RequestBody RegisterRequestDto req) {
        ResponseUserDto response = this.authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
