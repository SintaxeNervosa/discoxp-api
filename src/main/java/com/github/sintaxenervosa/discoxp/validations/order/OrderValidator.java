package com.github.sintaxenervosa.discoxp.validations.order;

import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;

public interface OrderValidator {
    void validateAddOrder(OrderRequestDTO orderRequestDTO);
}
