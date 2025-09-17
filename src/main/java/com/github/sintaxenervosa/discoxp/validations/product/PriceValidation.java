package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

import java.math.BigDecimal;

public interface PriceValidation {
    default void validatePriceProduct(BigDecimal price) {
        if (price == null) {
            ValidationErrorRegistry.addError("O preço não pode ser vazio.");
            return;
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            ValidationErrorRegistry.addError("O preço não pode ser negativo.");
            return;
        }

        if (price.scale() > 2) {
            ValidationErrorRegistry.addError("O preço deve ter no máximo 2 casas decimais.");
        }
    }
}
