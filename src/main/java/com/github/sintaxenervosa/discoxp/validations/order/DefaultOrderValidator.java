package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.order.InvalidOrderException;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderValidator implements OrderValidator, QuantityValidator, PaymentValidator, FreteValidator, AddressValidator {

    @Override
    public void validateAddOrder(OrderRequestDTO request) {
        if(request.userId().isBlank()) {
            ValidationErrorRegistry.addError("Informe o id do usuário");
        } for(ProductAndQuantityRequestDTO p : request.products()) {
            if(p.productId().isBlank()) {
                ValidationErrorRegistry.addError("Informe o id do produto");
            }
        }

        // validar a quantidade
        validateQuantity(request.products());

        // validar o forma de pagamento
        validatePayment(request.paymentMethod());

        // validar o frete
        validateFrete(request.freight());

        // validar endereço de entrega
        validateDeliveryAddress(request.deliveryAddressId());

        if (!ValidationErrorRegistry.hasErrors()) {
            return;
        }
        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        throw new InvalidOrderException(errorMessage);
    }
}
