package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO request) {
            userService.createUser(request);
            return ResponseEntity.status(201).body("User created");
    };

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDTO request) {
        userService.updateUser(request);
        return ResponseEntity.status(201).body("Usuário alterado");
    };
}
