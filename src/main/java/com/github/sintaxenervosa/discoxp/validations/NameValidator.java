package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;

public interface NameValidator {

    default void validateName(String name) {
        if(name == null || name.isEmpty()) {
            throw new InvalidUserDataException("Informe o nome");
        }

        name.chars().forEach(c -> {
            if(Character.isDigit(c)) {
                throw new InvalidUserDataException("Não pode conter números");
            };
        });
    }
}
