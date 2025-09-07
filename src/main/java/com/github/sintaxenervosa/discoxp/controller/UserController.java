package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.user.UserDto;
import com.github.sintaxenervosa.discoxp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.LoginResponseDto;
import com.github.sintaxenervosa.discoxp.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto login = userService.loginUser(loginRequestDto);
        return ResponseEntity.status(200)
            .body(login);
    }

    @GetMapping("/users/{id}") // Retornar DTO
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public List<UserDto> listar() {
        return userService.findAllUsers()
                .stream()
                .map(UserDto::new)
                .toList();
    }
}
