package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultProductValidator implements ProductValidator, NameValidator, DescriptionValidation, EvaluationValidation, PriceValidation, QuantityValidation {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void validateProductCreation(CreateProductRequestDTO request) {

        if (productRepository.existsByName(request.name())) {
            throw new IllegalArgumentException("Já existe um produto com esse nome.");
        }

        validateNameProduct(request.name());
        ValidationErrorRegistry.addError("Nome do produto inválido ou vazio.");

        validateDescriptionProduct(request.description());
        ValidationErrorRegistry.addError("Descrição do produto inválida.");

        validateEvaluationProduct(request.evaluation());
        ValidationErrorRegistry.addError("Avaliação fora do intervalo permitido.");

        validatePriceProduct(request.price());
        ValidationErrorRegistry.addError("Preço do produto inválido.");

        validateQuantityProduct(request.quantity());
        ValidationErrorRegistry.addError("Quantidade do produto deve ser maior que zero.");

    }

    @Override
    public void validateProductUpdate(UpdateProductRequestDTO request) {

    }

    @Override
    public void validateFindProductById(String id) {

    }

    @Override
    public void validateChangeProductStatus(String id) {

    }
}
