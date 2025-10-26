package com.github.sintaxenervosa.discoxp.dto.client;

import io.micrometer.common.lang.Nullable;

import java.time.LocalDate;

public record UpdateClientRequestDTO(String name, String password, LocalDate dateOfBirth, String gender) {
}
