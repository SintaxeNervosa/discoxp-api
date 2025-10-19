package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;
import io.micrometer.common.lang.Nullable;

import java.time.LocalDate;

public record UpdateUserRequestDTO(String id, String name, String group, String cpf, String password, @Nullable LocalDate dateOfBirth,@Nullable String gender) {
}
