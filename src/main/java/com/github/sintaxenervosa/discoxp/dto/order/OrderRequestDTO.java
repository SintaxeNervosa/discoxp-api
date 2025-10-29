package com.github.sintaxenervosa.discoxp.dto.order;

import io.micrometer.common.lang.Nullable;

public record OrderRequestDTO(
        String userId,
        String quantity,
        String paymentMethod,
        String frete,
        @Nullable String deliveryAddressId, // caso não tenha, pega o default
        String ...productId) {
}
