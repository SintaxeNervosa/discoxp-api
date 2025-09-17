package com.github.sintaxenervosa.discoxp.validations.product;

public interface DescriptionValidation {

    default void validateDescriptionProduct(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }
        if (description.length() > 2000) {
            throw new IllegalArgumentException("A descrição não pode ter mais de 2000 caracteres.");
        }
    }

}
