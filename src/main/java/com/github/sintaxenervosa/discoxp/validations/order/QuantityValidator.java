package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface QuantityValidator {
    default void validateQuantity(String quantity) {
        if(quantity.isBlank()) {
            ValidationErrorRegistry.addError("Informe a quantidade do produto");
            return;
        }

        int number = 0;

        try {
            number = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            ValidationErrorRegistry.addError("Quantidade inválida");
            return;
        }

        if(number <= 0) {
            ValidationErrorRegistry.addError("Quantidade não pode ser <= a 0.");
        }
    }
}
