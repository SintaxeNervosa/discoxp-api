package com.github.sintaxenervosa.discoxp.validations.product;

public interface QuantityValidation {

    default void validate(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
    }


}
