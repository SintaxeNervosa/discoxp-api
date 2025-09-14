package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface NameValidator {

    default void validateNameProduct(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (name.length() > 200) {
            throw new IllegalArgumentException("O nome não pode ter mais de 200 caracteres.");
        }
    }

}
