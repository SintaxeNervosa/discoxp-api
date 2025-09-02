package com.github.sintaxenervosa.discoxp.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.LoginResponseDto;
import com.github.sintaxenervosa.discoxp.service.UserService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        try {
            Optional<LoginResponseDto> resp = userService.login(loginRequestDto);

           return resp
                .map(user -> ResponseEntity.ok()
                    .body(Map.of(
                        "message", "Login realizado com sucesso!",
                        "user", user
                    )))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email ou senha invalidosss")));

        } catch (Exception e) {
            return  ResponseEntity.badRequest()
            .body(Map.of("error", e.getMessage()));
        }
    }
}
