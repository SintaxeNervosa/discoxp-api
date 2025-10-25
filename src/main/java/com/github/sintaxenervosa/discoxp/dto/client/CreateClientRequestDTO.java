package com.github.sintaxenervosa.discoxp.dto.client;

public record CreateClientRequestDTO (String id, String name, String email, String cpf, String password, String dateOfBirth, String gender){}
