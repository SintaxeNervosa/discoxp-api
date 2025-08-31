package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.User;

public interface UserValidator<T extends User> extends Validator<T>, NameValidator, EmailValidator, PasswordValidator {

    default void validate(T object) {
        validateName(object.getName());
        try {
            validateEmail(object.getEmail());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        validatePassword(object.getPassword());
    }

}
