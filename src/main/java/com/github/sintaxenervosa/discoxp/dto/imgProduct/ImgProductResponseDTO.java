package com.github.sintaxenervosa.discoxp.dto.imgProduct;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;

public record ImgProductResponseDTO(
        String id,
        String name,
        String imageUrl) {
    public static ImgProductResponseDTO fromEntity(ImageProduct imageProduct) {

        return new ImgProductResponseDTO(
            imageProduct.getId().toString(),
            imageProduct.getName(),
            "images" + imageProduct.getId()
        );
    }
}
