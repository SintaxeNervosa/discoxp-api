package com.github.sintaxenervosa.discoxp.dto.client;

import java.time.LocalDate;

public record CreateClientRequestDTO (
        String name,
        String email,
        String cpf,
        String password,
        LocalDate dateOfBirth,
        String gender){}
