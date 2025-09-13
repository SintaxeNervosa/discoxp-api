package com.github.sintaxenervosa.discoxp.dto.product;

import com.github.sintaxenervosa.discoxp.model.Product;

import java.math.BigDecimal;

public record CreateProductRequestDTO(String name, double evaluation, String description, BigDecimal price, int quantity) {

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setEvaluation(this.evaluation);
        return product;
    }
}
