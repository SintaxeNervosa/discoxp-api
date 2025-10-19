package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface GenderValidator {

    default void validateGender(String gender) {
        if (gender == null || gender.isBlank()) {
            ValidationErrorRegistry.addError("Gênero é obrigatório");
            return;
        }
    }
}
