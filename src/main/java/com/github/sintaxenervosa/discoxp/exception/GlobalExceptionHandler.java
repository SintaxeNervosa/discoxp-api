package com.github.sintaxenervosa.discoxp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.github.sintaxenervosa.discoxp.dto.error.ErrorResponse;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;

@ControllerAdvice
public class GlobalExceptionHandler {

    // exceção para usuário não encontrado 
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundExeption error) {
        ErrorResponse errorResponse = new ErrorResponse(error.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }
}
