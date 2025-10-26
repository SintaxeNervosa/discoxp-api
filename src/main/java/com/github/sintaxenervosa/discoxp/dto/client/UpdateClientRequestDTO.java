package com.github.sintaxenervosa.discoxp.dto.client;

import io.micrometer.common.lang.Nullable;

public record UpdateClientRequestDTO(String name, String password, String dateOfBirth, String gender) {
}
