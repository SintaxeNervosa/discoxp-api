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

    // @GetMapping
    // public List<User> listar() {
    //     return userService.findAllUsers();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
    //     return userService.findUserById(id)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // @PostMapping
    // public ResponseEntity<User> salvar(@RequestBody User user) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<User> alterar(@PathVariable Long id, @RequestBody User user) {
    //     try {
    //         return ResponseEntity.ok(userService.updateUser(id, user));
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @PatchMapping("/{id}/status")
    // public ResponseEntity<User> alterarStatus(@PathVariable Long id) {
    //     try {
    //         return ResponseEntity.ok(userService.statsUser(id));
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}
