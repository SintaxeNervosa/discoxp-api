package com.github.sintaxenervosa.discoxp.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.service.UserService;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        try {
            Optional<User> resp = userService.login(loginRequestDto);

            return resp
                .map(user -> ResponseEntity.ok().body("Login realizado com sucesso!"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha invalidos"));

        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
