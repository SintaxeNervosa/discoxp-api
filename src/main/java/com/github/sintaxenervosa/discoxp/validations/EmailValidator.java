package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.repository.UserRepository;

public interface EmailValidator {

    UserRepository getUserRepository(); // para quem implementar, é necessario fornecer

    default void validateEmail(String email) {
        // implementacao
    }
}
