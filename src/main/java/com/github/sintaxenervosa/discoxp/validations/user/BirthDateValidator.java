package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

import java.time.LocalDate;

public interface BirthDateValidator {

    default void validateBirthDate(LocalDate birthDate){
    LocalDate now = LocalDate.now();
    LocalDate limiteMinimo = LocalDate.now().minusYears(120);

        if(birthDate ==  null){
            ValidationErrorRegistry.addError("Data de nascimento é obrigatória");
            return;
        }
        if (birthDate.isAfter(now)) {
            ValidationErrorRegistry.addError("Data de invalida!");
            return;
        }
        if (birthDate.isBefore(limiteMinimo)) {
            ValidationErrorRegistry.addError("Data de invalida!");
            return;
        }

    }

}
