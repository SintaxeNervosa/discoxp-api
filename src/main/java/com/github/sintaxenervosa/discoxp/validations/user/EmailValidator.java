package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface EmailValidator {

    UserRepository userRepository(); // para quem implementar, é necessario fornecer

    // Valida o formato do e-mail
    default boolean validateFormatEmail(String email) { // implementacao
       if(email == null || email.isBlank()){
           ValidationErrorRegistry.addError("E-mail não pode ser vazio");
           return false;
       };

       String emailRegex = "^[\\w-\\.']+@([\\w-]+\\.)+[\\w-]{2,4}$";
       if(!email.matches(emailRegex)){
           ValidationErrorRegistry.addError("Formato de e-mail inválido");
           return false;
       };

       return true;
    }

    default boolean validateExistsByEmail(String email) {
        return userRepository().existsByEmail(email);
    }
}
