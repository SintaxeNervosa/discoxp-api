package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface FreteValidator {
    default void validateFrete(String frete) {
        if(frete == null || frete.isEmpty()) {
            ValidationErrorRegistry.addError("Informe o frete.");
            return;
        }

        int value = 0;

        try {
            value = Integer.parseInt(frete); // arrumar
        } catch (IllegalArgumentException e) {
            ValidationErrorRegistry.addError("Frete inválido.");
            return;
        }

        if(value < 0) {
            ValidationErrorRegistry.addError("Frete não pode ser menor que zero.");
        }
    }
}
