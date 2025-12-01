package com.github.sintaxenervosa.discoxp.dto.order.orderItem;

import com.github.sintaxenervosa.discoxp.model.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO (Long produtId, String name, Integer quantity, BigDecimal unitPrice, byte[] imageFile) {
    public static OrderItemResponseDTO fromEntity(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getProduct().getPrice(),
                orderItem.getProduct().getImages().get(0).getImageData()
        );
    }
}
