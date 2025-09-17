package com.github.sintaxenervosa.discoxp.dto.product;

import java.math.BigDecimal;

public record UpdateProductRequestDTO(String id, String name, double evaluation, String description, BigDecimal price, Integer quantity) {
}
