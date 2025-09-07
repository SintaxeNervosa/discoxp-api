package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;

public interface ProductValidator {
    void validateProductCreation(CreateProductRequestDTO request);
    void validateProductUpdate(UpdateProductRequestDTO request);
    void validateFindProductById(String id);
    void validateChangeProductStatus(String id);
}
