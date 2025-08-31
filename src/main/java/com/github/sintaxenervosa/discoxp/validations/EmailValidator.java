package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.repository.UserRepository;

public interface EmailValidator {

    UserRepository getUserRepository(); // para quem implementar, é necessario fornecer

    default void validateEmail(String email) throws IllegalAccessException { // implementacao
       if(email == null || email.isBlank()){
           throw new IllegalAccessException("E-mai não pode ser vazio");
       }

       String emailRegex = "^[\\w-\\.']+@([\\w-]+\\.)+[\\w-]{2,4}$";
       if(!email.matches(emailRegex)){
           throw new IllegalAccessException("Formato de e-mail inválido");
       }

       if(getUserRepository().existsByEmail(email)){
           throw new IllegalAccessException("E-mail já cadastrado");
       }
    }
}
