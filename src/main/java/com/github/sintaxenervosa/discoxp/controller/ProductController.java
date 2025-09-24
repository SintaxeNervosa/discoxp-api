package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.ProductResponseDTO;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDTO request){
        try {
        productService.createProduct(request);
        return ResponseEntity.status(200).body("Produto criado!");
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
        ProductResponseDTO productResponseDTO  = productService.findProductById(id);
        return ResponseEntity.status(200).body(productResponseDTO);
    }

    @GetMapping("/productsByName")
    public ResponseEntity<Page<Product>> findProductByName(@RequestParam("name") String name, Pageable pageable) {
        return ResponseEntity.ok(productService.findProductByName(name, pageable));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponseDTO>> listProducts(Pageable pegeable){
        return ResponseEntity.ok(productService.listAllProducts(pegeable));
    }

}
