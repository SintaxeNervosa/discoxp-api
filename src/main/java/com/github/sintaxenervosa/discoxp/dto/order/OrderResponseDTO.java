package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.dto.order.orderItem.OrderItemResponseDTO;
import com.github.sintaxenervosa.discoxp.model.OrderStatus;
import io.micrometer.common.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponseDTO (
        Integer orderId,
        LocalDate orderDate,
        OrderStatus status,
        BigDecimal totalPrice,
        @Nullable List<OrderItemResponseDTO> orderItemResponseDTOList) { }
