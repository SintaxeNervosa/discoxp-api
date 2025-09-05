package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;

public record CreateUserRequestDTO(String name, String email, String group, String password, String cpf) { }
