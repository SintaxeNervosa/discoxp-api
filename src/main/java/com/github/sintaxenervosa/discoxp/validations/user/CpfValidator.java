package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import top.colman.simplecpfvalidator.CpfValidatorKt;


import java.util.List;

public interface CpfValidator {
    UserRepository userRepository();

    default boolean validateFormatCpf(String cpf) {

        //isCpf(CPF, Caracteres a serem ignorados em caso de cpf com máscara (111.111.111-11))
        if(!CpfValidatorKt.isCpf(cpf, List.of('.', '-'))) { // Valida o formato do CPF
            ValidationErrorRegistry.addError("CPF inválido");
            return false;
        }
        return true;
    }

    default boolean validateExistsByCpf(String cpf) {
        return userRepository().existsByCpf(cpf);
    }
}
