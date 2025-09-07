package com.github.sintaxenervosa.discoxp.validations;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorRegistry {
    private static List<String> errors;

    private ValidationErrorRegistry() {};

    public static String getErrorMessage() {
        getInstance();

        if(errors.isEmpty()) { return null; }

        StringBuilder errorMessage = new StringBuilder();

        for(String error : errors)  {
            errorMessage.append(error).append(", ");
        }

        errors.clear();
        return errorMessage.toString();
    }

    public static void addError(String error) {
        getInstance();

        errors.add(error);
    }

    // método para criar a instância da lista
    public static void getInstance() {
        if(errors == null) {
            errors = new ArrayList<>();
        }
    }

    public static boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
