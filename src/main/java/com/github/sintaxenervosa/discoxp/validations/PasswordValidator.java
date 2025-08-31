package com.github.sintaxenervosa.discoxp.validations;

public interface PasswordValidator {

    default void validatePassword(String password) {
        // validation
    }
}
