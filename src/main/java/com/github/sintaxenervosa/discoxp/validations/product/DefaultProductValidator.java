package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.product.InvalidProductDataException;
import com.github.sintaxenervosa.discoxp.exception.product.ProductNotFoundException;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import org.springframework.stereotype.Component;


@Component
public class DefaultProductValidator implements ProductValidator, NameValidator, DescriptionValidation, EvaluationValidation, PriceValidation, QuantityValidation {

    private final ProductRepository productRepository;

    public DefaultProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        if(request.id() == null || request.id().isEmpty()) {
            throw new InvalidProductDataException("Informe o id do produto.");
        }

        long id = 0;

        try {
            id = Long.parseLong(request.id());
        } catch (NumberFormatException e) {
            throw new InvalidProductDataException("ID inválido");
        }

        Product product = productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Produto com o id " + request.id() + " não encontrado."));

        // validação de nome
        if(validateNameProduct(request.name())) {
            if(!product.getName().equalsIgnoreCase(request.name())) {
                boolean exists = validaExistsByName(request.name());

                if(exists) { ValidationErrorRegistry.addError("Nome já utilizado em outro produto."); }
            }
        }

        // validação de descrição
        validateDescriptionProduct(request.description());

        // validação de avaliação
        validateEvaluationProduct(request.evaluation());

        // validação de preço
        validatePriceProduct(request.price());

        // validação de quantidade
        validateQuantityProduct(request.quantity());

        if(!ValidationErrorRegistry.hasErrors()) {
            return;
        }

        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        throw new InvalidProductDataException(errorMessage);
    }

    @Override
    public void validateFindProductById(String id) {

    }

    @Override
    public void validateChangeProductStatus(String id) {

    }

    @Override
    public ProductRepository productRepository() {
        return productRepository;
    }
}
