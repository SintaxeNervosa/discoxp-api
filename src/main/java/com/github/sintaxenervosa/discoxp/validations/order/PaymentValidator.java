package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.model.PaymentMethod;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface PaymentValidator {
    default void validatePayment(String methodPayment) {
            if(methodPayment.isBlank()) {
                ValidationErrorRegistry.addError("Informe a forma de pagamento.");
            }

            try {
                PaymentMethod.valueOf(methodPayment);
            } catch (IllegalArgumentException e) {
                ValidationErrorRegistry.addError("Forma de pagamento inválida.");
            }
    }
}
