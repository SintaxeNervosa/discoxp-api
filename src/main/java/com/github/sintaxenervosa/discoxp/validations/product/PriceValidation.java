package com.github.sintaxenervosa.discoxp.validations.product;

import java.math.BigDecimal;

public interface PriceValidation {
    default void validatePriceProduct(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("O preço não pode ser vazio.");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }

        if (price.scale() > 2) {
            throw new IllegalArgumentException("O preço deve ter no máximo 2 casas decimais.");
        }
    }

}
