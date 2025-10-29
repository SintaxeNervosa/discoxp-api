package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.order.InvalidOrderException;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderValidator implements OrderValidator, QuantityValidator, PaymentValidator, FreteValidator {

    @Override
    public void validateAddOrder(OrderRequestDTO request) {
        if(request.userId().isBlank()) {
            ValidationErrorRegistry.addError("Informe o id do usuário");
        } if(request.productId().length < 1) {
            ValidationErrorRegistry.addError("Informe o id do produto");
        }

        // validar a quantidade
        validateQuantity(request.quantity());

        // validar o forma de pagamento
        validatePayment(request.paymentMethod());

        // validar o frete
        validateFrete(request.frete());

        if(request.deliveryAddressId() != null) {
            try {
                Integer.parseInt(request.deliveryAddressId());
            } catch (IllegalArgumentException e) {
                ValidationErrorRegistry.addError("ID de endero inválido.");
            }
        }

        if (!ValidationErrorRegistry.hasErrors()) {
            return;
        }
        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        throw new InvalidOrderException(errorMessage);
    }
}
