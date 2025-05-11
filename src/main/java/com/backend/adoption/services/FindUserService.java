package com.backend.adoption.services;

import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.entities.User;
import com.backend.adoption.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class FindUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;

    public ResponseUserDto execute(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim(); // Remove o prefixo e espaços desnecessários
        }

        String email = jwtService.getUsernameFromToken(token);

        User user = this.userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User with this email was not found: " + email ));

        return new ResponseUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getPhone(),
                user.getCreatedAt()
        );
    }
}
