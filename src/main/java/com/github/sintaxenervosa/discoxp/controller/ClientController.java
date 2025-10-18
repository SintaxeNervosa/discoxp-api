package com.github.sintaxenervosa.discoxp.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.service.ClientService;
import com.github.sintaxenervosa.discoxp.service.UserService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final UserService userService;
    private final ClientService clientService;

    public ClientController(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<HttpStatusCode> create(@RequestBody CreateUserRequestDTO request) {
        userService.createUser(request);
        return null;
    }

}
