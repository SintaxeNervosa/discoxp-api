package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import top.colman.simplecpfvalidator.CpfValidatorKt;

import java.util.List;

public interface CpfValidator {
    UserRepository userRepository();

    default void validateFormatCpf(String cpf) {

        //isCpf(CPF, Caracteres a serem ignorados em caso de cpf com máscara (111.111.111-11))
        if(!CpfValidatorKt.isCpf(cpf, List.of())) { // Valida o formato do CPF
            throw new InvalidUserDataException(("CPF inválido"));
        }
    }

    default boolean validateExistsByCpf(String cpf) {
        return userRepository().existsByCpf(cpf);
    }
}
