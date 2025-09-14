package com.github.sintaxenervosa.discoxp.validations.product;

public interface QuantityValidation {

    default void validateQuantityProduct(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
    }


}
