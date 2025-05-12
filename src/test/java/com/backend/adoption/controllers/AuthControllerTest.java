package com.backend.adoption.controllers;

import com.backend.adoption.dto.users.RegisterRequestDto;
import com.backend.adoption.dto.users.ResponseUserDto;
import com.backend.adoption.services.AuthService;
import com.backend.adoption.services.LoginService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private LoginService loginService;

    @Test
    public void testRegisterUserSuccessfully() throws Exception {
        RegisterRequestDto requestDto = new RegisterRequestDto(
                "João da Silva",
                "joao@email.com",
                "senha123",
                "senha123",
                "808.607.974-11",
                "(24) 0006-0725"
        );

        ResponseUserDto responseDto = new ResponseUserDto(
                1L,
                "João da Silva",
                "joao@email.com",
                "808.607.974-11",
                "(24) 0006-0725",
                new Date()
        );

        Mockito.when(authService.register(any(RegisterRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "João da Silva",
                                    "email": "joao@email.com",
                                    "password": "senha123",
                                    "confirmPassword": "senha123",
                                    "cpf": "808.607.974-11",
                                    "phone": "(24) 0006-0725"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "name": "João da Silva",
                            "email": "joao@email.com",
                            "cpf": "808.607.974-11",
                            "phone": "(24) 0006-0725"
                        }
                        """));
    }

    @Test
    public void testRegisterUserWithInvalidData() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "",
                                    "email": "invalid-email",
                                    "password": "123",
                                    "confirmPassword": "123",
                                    "cpf": "123.456.789-10",
                                    "phone": "(24) 00060725"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}