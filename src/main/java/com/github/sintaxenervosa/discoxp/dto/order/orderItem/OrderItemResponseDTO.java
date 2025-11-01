package com.github.sintaxenervosa.discoxp.dto.order.orderItem;

import com.github.sintaxenervosa.discoxp.model.OrderItem;

public record OrderItemResponseDTO (String name, Integer quantity, byte[] imageData) {
    public static OrderItemResponseDTO fromEntity(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getProduct().getImages().getFirst().getImageData()
        );
    }
}
