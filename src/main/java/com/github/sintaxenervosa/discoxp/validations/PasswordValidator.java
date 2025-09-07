package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;

public interface PasswordValidator {

    default void validatePassword(String password) {
        if(password.isEmpty()) {
            UserValidator.addErroMessageInList("Informe a senha");
            return;
        }

        StringBuilder errors = new  StringBuilder(5);

        if(password.length() < 8) {
            errors.append("A senha deve ter no minimo 8 caracteres, ");
        }

        boolean uppercaseLetter = false;
        boolean lowercaseLetter = false;
        boolean specialCharacter = false;
        boolean digit = false;

        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                uppercaseLetter = true;
            } else if(Character.isLowerCase(c)) {
                lowercaseLetter = true;
            } else if(Character.isDigit(c)) {
                digit = true;
            } else if(!Character.isLetterOrDigit(c)) {
                specialCharacter = true;
            }
        }

        if(!uppercaseLetter) {
            errors.append("A senha de ter letra maiuscula, ");
        }
        if(!lowercaseLetter) {
            errors.append("A senha de ter letra minuscula, ");
        }
        if(!digit) {
            errors.append("A senha de ter numeros, ");
        }
        if(!specialCharacter) {
            errors.append("A senha de ter caracter especial, ");
        }

        if(!errors.isEmpty()){
            UserValidator.addErroMessageInList(errors.toString());
        }
    }
}
