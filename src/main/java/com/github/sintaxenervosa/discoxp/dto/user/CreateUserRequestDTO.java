package com.github.sintaxenervosa.discoxp.dto.user;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;

public record   CreateUserRequestDTO(
        String name,
        String email,
        String group,
        String password,
        String cpf,
        @Nullable LocalDate dateOfBirth,
        @Nullable String gender) {
}
