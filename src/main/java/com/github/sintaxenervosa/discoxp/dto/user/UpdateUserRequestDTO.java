package com.github.sintaxenervosa.discoxp.dto.user;

import io.micrometer.common.lang.Nullable;

import java.time.LocalDate;

// Edit de ADMIN E STOCKIST
public record UpdateUserRequestDTO(
        String id,
        String name,
        String group,
        String cpf,
        String password,
        @Nullable LocalDate dateOfBirth,
        @Nullable String gender) {
}
