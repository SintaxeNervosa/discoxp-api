package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;

public interface PasswordValidator {

    default void validatePassword(String password) {
        if(password.length() < 8) {
//            throw new IllegalArgumentException("Senha inválida");
            throw new InvalidUserDataException("Senha inválida");
        }
    }
}
