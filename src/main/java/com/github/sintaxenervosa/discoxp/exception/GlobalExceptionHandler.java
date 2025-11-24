package com.github.sintaxenervosa.discoxp.exception;

import com.github.sintaxenervosa.discoxp.exception.address.DuplicateAddressException;
import com.github.sintaxenervosa.discoxp.exception.address.InvalidAddressException;
import com.github.sintaxenervosa.discoxp.exception.order.InvalidOrderException;
import com.github.sintaxenervosa.discoxp.exception.order.OrderNotFoundException;
import com.github.sintaxenervosa.discoxp.exception.product.InvalidProductDataException;
import com.github.sintaxenervosa.discoxp.exception.product.ProductNotFoundException;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.github.sintaxenervosa.discoxp.dto.error.ErrorResponse;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // observa todas as exceções lancadas
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundExeption.class) // chama a funcão caso receba a exceção "UserNotFoundExeption"
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundExeption error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserDataException(InvalidUserDataException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(InvalidProductDataException.class)
    ResponseEntity<ErrorResponse> handleInvalidProductDataException(InvalidProductDataException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(InvalidAddressException.class)
    ResponseEntity<ErrorResponse> handleProductNotFoundException(InvalidAddressException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(DuplicateAddressException.class)
    ResponseEntity<ErrorResponse> handleProductNotFoundException() {
        ErrorResponse errorResponse = new ErrorResponse("Não foi possível inserir o endereço.");
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(InvalidOrderException.class)
    ResponseEntity<ErrorResponse> handleOrderDataException(InvalidOrderException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }
}
