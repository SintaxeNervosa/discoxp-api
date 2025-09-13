package com.github.sintaxenervosa.discoxp.dto.product;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public ProductDto(Long id, String name, String description, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
