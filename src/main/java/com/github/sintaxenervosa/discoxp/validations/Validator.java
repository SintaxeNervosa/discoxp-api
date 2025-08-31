package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.User;

public interface Validator<T extends User> {
    void validate(T value) throws IllegalAccessException;
}
