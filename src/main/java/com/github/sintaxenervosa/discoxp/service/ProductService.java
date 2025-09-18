package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.product.DefaultProductValidator;
import com.github.sintaxenervosa.discoxp.validations.product.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final DefaultProductValidator defaultProductValidator;

    public ProductService(ProductRepository productRepository,ProductValidator productValidator, DefaultProductValidator defaultProductValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
        this.defaultProductValidator = defaultProductValidator;
    }

    public void createProduct(CreateProductRequestDTO request) {
        productValidator.validateProductCreation(request);
        Product product = request.toEntity();
        productRepository.save(product);
    }

    public void updateProduct(UpdateProductRequestDTO request) {
        defaultProductValidator.validateProductUpdate(request);

        Product newProduct = new Product(request);
        Long parseIdToLong = Long.parseLong(request.id());
        newProduct.setId(parseIdToLong);

        productRepository.save(newProduct);
    }

    // temporário
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

}
