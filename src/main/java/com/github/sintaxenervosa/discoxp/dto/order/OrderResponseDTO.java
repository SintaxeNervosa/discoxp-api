package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.dto.order.orderItem.OrderItemResponseDTO;
import com.github.sintaxenervosa.discoxp.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponseDTO (
        Integer orderId,
        LocalDate orderDate,
        OrderStatus status,
        BigDecimal totalPrice,
        List<OrderItemResponseDTO> orderItemResponseDTOList) { }
