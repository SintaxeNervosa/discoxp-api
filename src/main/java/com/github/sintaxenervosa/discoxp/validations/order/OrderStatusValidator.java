package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.dto.order.ChangeOrderStatusRequestDTO;
import com.github.sintaxenervosa.discoxp.model.OrderStatus;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface OrderStatusValidator {
    default void validateStatus(ChangeOrderStatusRequestDTO request) {
        if(request.status() == null || request.status().isBlank()) {
            ValidationErrorRegistry.addError("Informe o status.");
            return;
        }

        try {
            OrderStatus.valueOf(request.status());
        } catch (IllegalArgumentException e) {
            ValidationErrorRegistry.addError("Status inválido.");
        }
    }
}
