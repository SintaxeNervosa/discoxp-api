package com.github.sintaxenervosa.discoxp.dto.client;

import io.micrometer.common.lang.Nullable;

public record UpdateClientRequestDTO(String id, String name, String group, String cpf, String password, String dateOfBirth, String gender) {
}
