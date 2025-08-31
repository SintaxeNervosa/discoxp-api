package com.github.sintaxenervosa.discoxp.validations;

public class Validation {
    public boolean isNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
