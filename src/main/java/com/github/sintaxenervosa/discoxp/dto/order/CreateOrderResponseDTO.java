package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.model.Order;

import java.math.BigDecimal;

public record CreateOrderResponseDTO(Integer id, BigDecimal total) {
    public static CreateOrderResponseDTO fromEntity(Order order) {
        return new CreateOrderResponseDTO(order.getOrderId(), order.getTotalOrderItems().add(order.getFreight()));
    }

}
