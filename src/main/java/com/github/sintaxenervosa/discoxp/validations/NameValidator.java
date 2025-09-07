package com.github.sintaxenervosa.discoxp.validations;

public interface NameValidator {

    default void validateName(String name) {
        if(name == null || name.isEmpty()) {
            UserValidator.addErroMessageInList("Informe o nome");
            return;
        }

        // verifica se o nome tem número
        name.chars().forEach(c -> {
            if(Character.isDigit(c)) {
                UserValidator.addErroMessageInList("Não pode conter números");
                return;
            };
        });
    }
}
