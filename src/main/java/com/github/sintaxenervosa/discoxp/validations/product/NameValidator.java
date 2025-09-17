package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface NameValidator {

    ProductRepository productRepository();

    default boolean validateNameProduct(String name) {
        if (name == null || name.trim().isEmpty()) {
            ValidationErrorRegistry.addError("O nome não pode ser vazio.");
            return false;
        }

        if (name.length() > 200) {
            ValidationErrorRegistry.addError("O nome não pode ter mais de 200 caracteres.");
            return false;
        }

        return true;
    }

    default boolean validaExistsByName(String name) {
        return productRepository().existsByName(name);
    }
}
