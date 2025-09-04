package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;

public record UpdateUserRequestDTO(String id, String name, String group, String cpf) {
}
