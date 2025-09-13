package com.github.sintaxenervosa.discoxp.dto.product;

import java.math.BigDecimal;

public record CreateProductRequestDTO(String name, double evaluation, String description, BigDecimal price, int quantity) {

}
