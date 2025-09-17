package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface DescriptionValidation {

    default void validateDescriptionProduct(String description) {
        if (description == null || description.trim().isEmpty()) {
            ValidationErrorRegistry.addError("A descrição não pode ser vazia.");
            return;
        }
        if (description.length() > 2000) {
            ValidationErrorRegistry.addError("A descrição não pode ter mais de 2000 caracteres.");
        }
    }

}
