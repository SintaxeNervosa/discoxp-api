package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.ProductResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.product.DefaultProductValidator;
import com.github.sintaxenervosa.discoxp.validations.product.ProductValidator;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final DefaultProductValidator defaultProductValidator;
    private final ImgProductRepository imgProductRepository;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator,
            DefaultProductValidator defaultProductValidator, ImgProductRepository imgProductRepository) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
        this.defaultProductValidator = defaultProductValidator;
        this.imgProductRepository = imgProductRepository;
    }

    public Product createProduct(CreateProductRequestDTO request) {
        productValidator.validateProductCreation(request);
        Product product = request.toEntity();
        return productRepository.save(product);
    }

    public void updateProduct(UpdateProductRequestDTO request) {
        defaultProductValidator.validateProductUpdate(request);

        Product newProduct = new Product(request);
        Long parseIdToLong = Long.parseLong(request.id());
        newProduct.setId(parseIdToLong);

        productRepository.save(newProduct);
    }

    public void updateProductQuantity(UpdateProductQuantityRequestDTO request) {
        defaultProductValidator.validateUpdateProductQuantity(request);

        Long parseIdToLong = Long.parseLong(request.id());
        Product product = productRepository.findById(parseIdToLong).get();
        product.setQuantity(request.quantity());

        productRepository.save(product);
    }

    // temporário
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductResponseDTO response = ProductResponseDTO.fromEntity(product);

        System.out.println(response);
        return response;
    }

    public Page<ProductResponseDTO> listAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAllByOrderByIdDesc(pageable);
        return productsPage.map(product -> ProductResponseDTO.fromEntity(product));
    }

    public Page<ProductResponseDTO> findProductByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCaseOrderByIdDesc(name, pageable);
        return products.map(product -> ProductResponseDTO.fromEntity(product));
    }

        public void changeProductStatus(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidUserDataException("Informe o ID do produto");
        }

        long optinalId = 0;

        try {
            optinalId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido");
        }

        Product product = productRepository.findById(optinalId).orElseThrow(
                () -> new UserNotFoundExeption("Usuário não encontrado"));

        product.setStatus(!product.isStatus());
        productRepository.save(product);
    }

}
