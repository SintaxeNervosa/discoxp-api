package com.github.sintaxenervosa.discoxp.dto.product;

import com.github.sintaxenervosa.discoxp.model.Product;
import java.math.BigDecimal;

public record ProductResponseDTO(long id, String name, double evaluation, String description, BigDecimal price, Integer quantity, boolean status) {
    public static ProductResponseDTO fromEntity(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getEvaluation(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.isStatus());
    };
}
