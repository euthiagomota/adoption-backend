package com.backend.adoption.controllers;

import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.services.FindUserService;
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
    public ResponseEntity<ResponseUserDto> findUser(@RequestHeader("Authorization") String authHeader) {
        ResponseUserDto response = this.findUserService.execute(authHeader);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
