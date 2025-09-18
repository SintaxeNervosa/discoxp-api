package com.github.sintaxenervosa.discoxp.dto.product;

import com.github.sintaxenervosa.discoxp.dto.imgProduct.ImgProductResponseDTO;
import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record ProductResponseDTO(long id, String name, double evaluation, String description, BigDecimal price, Integer quantity, List<ImgProductResponseDTO> images) {
    public static ProductResponseDTO fromEntity(Product product) {
        List<ImgProductResponseDTO> imagesList = new ArrayList<>();

        for (ImageProduct img : product.getImages()) {
            imagesList.add(ImgProductResponseDTO.fromEntity(img));
        }

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getEvaluation(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                imagesList);
    };
}
