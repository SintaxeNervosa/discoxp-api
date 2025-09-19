package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.product.CreateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.service.ProductService;
import com.github.sintaxenervosa.discoxp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @PutMapping("/change-status/{id}")
    public ResponseEntity<?> updateUserStatus(@PathVariable("id") String id) {
        userService.changeStatus(id);
        return ResponseEntity.status(201).body("User updated");
    }

    @PutMapping("/product")
    public HttpEntity<HttpStatusCode> updateProduct(@RequestBody UpdateProductRequestDTO product) {
        productService.updateProduct(product);
        return ResponseEntity.status(204).body(HttpStatus.NO_CONTENT);
    };

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO request) {
        userService.createUser(request);
        return ResponseEntity.status(201).body("User created");
    };

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDTO request) {
        System.out.println(request);
        userService.updateUser(request);

        return ResponseEntity.status(204).body(HttpStatus.NO_CONTENT);
    };

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDTO request){
        try {
            productService.createProduct(request);
            return ResponseEntity.status(200).body("Produto criado!");
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
    }

    @PutMapping("/product/status/{id}")
    public ResponseEntity<HttpStatusCode> changeProductStatus(@PathVariable("id") String id) {
        System.out.println(id);
        productService.changeProductStatus(id);
        return ResponseEntity.status(204).body(HttpStatus.NO_CONTENT);
    };

}
