package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.client.ExistsCpfResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.client.ExistsEmailResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/email/{email}")
    public ResponseEntity<ExistsEmailResponseDTO> emailExists(@PathVariable("email") String email) {
          return ResponseEntity.ok().body(userService.emailExists(email));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ExistsCpfResponseDTO> cpfExists(@PathVariable("cpf") String cpf) {
          return ResponseEntity.ok().body(userService.cpfExists(cpf));
    }
}
