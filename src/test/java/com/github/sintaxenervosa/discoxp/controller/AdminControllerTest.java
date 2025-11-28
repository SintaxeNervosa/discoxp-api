package com.github.sintaxenervosa.discoxp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.service.ProductService;
import com.github.sintaxenervosa.discoxp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
})
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @Test
    void erro201CriarUsuario() throws Exception {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "Carlos",
                "carlos@email.com",
                "ADMIN",
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void erro400ValidatorFalhar() throws Exception {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                "Carlos",
                "email-invalido",
                "ADMIN",
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        Mockito.doThrow(new InvalidUserDataException("E-mail inválido"))
                .when(userService).createUser(any());

        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
