package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.model.Gender;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface GenderValidator {

    default void validateGender(String gender) {
        if (gender == null || gender.isBlank()) {
            ValidationErrorRegistry.addError("Gênero é obrigatório");
            return;
        }

        try {
            Gender.valueOf(gender);
        } catch (IllegalArgumentException e) {
            ValidationErrorRegistry.addError("Gênero inválido");
        }
    }
}
