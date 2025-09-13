package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.product.DefaultProductValidator;
import com.github.sintaxenervosa.discoxp.validations.product.ProductValidator;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductValidator productValidator;
    private DefaultProductValidator defaultProductValidator;

    public ProductService(ProductRepository productRepository,ProductValidator productValidator, DefaultProductValidator defaultProductValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
        this.defaultProductValidator = defaultProductValidator;
    }

    public void createProduct(CreateProductRequestDTO request) {
        productValidator.validateProductCreation(request);
        Product product = new Product();
        productRepository.save(product);
    }

}
