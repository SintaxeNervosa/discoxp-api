package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;

public record CreateUserRequestDTO(String name, String email, Group group, String password, String cpf) { }
