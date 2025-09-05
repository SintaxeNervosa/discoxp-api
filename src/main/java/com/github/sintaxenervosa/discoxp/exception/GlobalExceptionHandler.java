package com.github.sintaxenervosa.discoxp.exception;

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
}
