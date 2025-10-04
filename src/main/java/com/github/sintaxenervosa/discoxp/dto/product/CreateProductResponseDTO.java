package com.github.sintaxenervosa.discoxp.dto.product;

import com.github.sintaxenervosa.discoxp.model.Product;

public record CreateProductResponseDTO (long id){
    public static CreateProductResponseDTO fromEntity(Product product){

        return new CreateProductResponseDTO(product.getId());
    }
}
