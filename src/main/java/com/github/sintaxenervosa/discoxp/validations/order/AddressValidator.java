package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface AddressValidator {
    default void validateDeliveryAddress(String addressId) {
        if(addressId.isBlank()) {
            ValidationErrorRegistry.addError("Informe o endereço.");
            return;
        }

        try {
            Long.parseLong(addressId);
        } catch (NumberFormatException e) {
            ValidationErrorRegistry.addError("Endereço inválido.");
        }
    }
}
