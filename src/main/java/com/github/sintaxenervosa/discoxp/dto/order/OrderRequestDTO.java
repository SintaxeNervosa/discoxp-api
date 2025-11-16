package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;

public record OrderRequestDTO(
        String userId,
        String paymentMethod,
        String freight,
        String deliveryAddressId,
        ProductAndQuantityRequestDTO ...products
    ) {
}
