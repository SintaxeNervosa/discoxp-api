package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface QuantityValidator {
    default void validateQuantity(ProductAndQuantityRequestDTO ...product) {
        int number;

        for(ProductAndQuantityRequestDTO p : product) {
            if(p.quantity().isBlank()) {
                ValidationErrorRegistry.addError("Informe a quantidade do produto");
                return;
            }

             number = 0;

            try {
                number = Integer.parseInt(p.quantity());
            } catch (NumberFormatException e) {
                ValidationErrorRegistry.addError("Quantidade inválida");
                return;
            }

            if(number <= 0) {
                ValidationErrorRegistry.addError("Quantidade não pode ser <= a 0.");
            }
        }
    }
}
