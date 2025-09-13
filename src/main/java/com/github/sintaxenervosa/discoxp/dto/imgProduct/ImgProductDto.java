package com.github.sintaxenervosa.discoxp.dto.imgProduct;

import com.github.sintaxenervosa.discoxp.model.Product;

public class ImgProductDto {

    private Long id;
    private String name;
    private byte[] imageData;
    private Product product;

    public ImgProductDto(Long id, String name, byte[] imageData, Product product) {
        this.id = id;
        this.name = name;
        this.imageData = imageData;
        this.product = product;
    }

}
