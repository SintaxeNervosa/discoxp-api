package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface QuantityValidation {

    default void validateQuantityProduct(Integer quantity) {
        if (quantity < 1) {
            ValidationErrorRegistry.addError("Quantidade não pode ser menor que 1");
        }
    }
}
