package com.github.sintaxenervosa.discoxp.dto.order;

import com.github.sintaxenervosa.discoxp.dto.order.orderItem.OrderItemResponseDTO;
import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;
import com.github.sintaxenervosa.discoxp.model.OrderStatus;
import com.github.sintaxenervosa.discoxp.model.PaymentMethod;
import io.micrometer.common.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponseDTO (
        Integer orderId,
        LocalDate orderDate,
        OrderStatus status,
        BigDecimal subtotal,
        DeliveryAddress deliveryAddress,
        PaymentMethod paymentMethod,
        BigDecimal freight,
            BigDecimal total,
        @Nullable List<OrderItemResponseDTO> orderItemResponseDTOList) { }
