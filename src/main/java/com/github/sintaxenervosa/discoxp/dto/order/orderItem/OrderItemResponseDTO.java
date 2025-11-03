package com.github.sintaxenervosa.discoxp.dto.order.orderItem;

import com.github.sintaxenervosa.discoxp.model.OrderItem;

public record OrderItemResponseDTO (Long produtId, byte[] imageFile, String name, Integer quantity) {
    public static OrderItemResponseDTO fromEntity(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getImages().get(0).getImageData(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity()
        );
    }
}
