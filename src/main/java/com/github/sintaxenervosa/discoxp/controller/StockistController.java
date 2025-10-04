package com.github.sintaxenervosa.discoxp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.service.ProductService;

@RestController
@RequestMapping("/stockist")
public class StockistController {

    private ProductService productService;

    public StockistController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/change-product")
    public ResponseEntity<HttpStatusCode> updateProductQuantity(@RequestBody UpdateProductQuantityRequestDTO request) {
        System.out.println("Objeto da requisição: " + request);
        productService.updateProductQuantity(request);
        return ResponseEntity.status(204).body(HttpStatus.NO_CONTENT);
    }
}
