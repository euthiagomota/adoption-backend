package com.backend.adoption.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.adoption.dto.users.RegisterRequestDto;
import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.entities.User;
import com.backend.adoption.entities.enuns.Role;
import com.backend.adoption.repositories.UserRepository;


@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseUserDto register(RegisterRequestDto req) {

        if(userRepository.existsByEmail(req.email())) {
            throw new RuntimeException("This email is already in use.");
        }

        if(!req.password().equals(req.confirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setCpf(req.cpf());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setPhone(req.phone());
        user.setRole(Role.USER);

        User userSaved = this.userRepository.save(user);

        ResponseUserDto response = new ResponseUserDto(
            userSaved.getId(), 
            userSaved.getName(), 
            userSaved.getEmail(),
            userSaved.getCpf(),
            userSaved.getPhone(),
            userSaved.getCreatedAt()
            );
            
        return response;
    }
}
