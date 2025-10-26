package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.client.*;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.model.User;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreateUserResponseDTO> create(@RequestBody CreateClientRequestDTO request) {
        CreateUserRequestDTO dto = new CreateUserRequestDTO(
                request.name(),
                request.email(),
                "CLIENT",
                request.password(),
                request.cpf(),
                request.dateOfBirth(),
                request.gender());

        return ResponseEntity.status(201).body(userService.createUser(dto));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ExistsEmailResponseDTO> emailExists(@PathVariable("email") String email) {
          return ResponseEntity.ok().body(userService.emailExists(email));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ExistsCpfResponseDTO> cpfExists(@PathVariable("cpf") String cpf) {
          return ResponseEntity.ok().body(userService.cpfExists(cpf));
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatusCode> updateClient(@RequestBody UpdateUserRequestDTO request) {
        clientService.changeClient(request);
        return ResponseEntity.status(200).body(HttpStatus.NO_CONTENT);
    } 
}
