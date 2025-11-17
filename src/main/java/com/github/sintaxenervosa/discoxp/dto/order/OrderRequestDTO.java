package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;

import java.util.List;

public record OrderRequestDTO(
        String userId,
        String paymentMethod,
        String freight,
        String deliveryAddressId,
        List<ProductAndQuantityRequestDTO> products
    ) {
}
