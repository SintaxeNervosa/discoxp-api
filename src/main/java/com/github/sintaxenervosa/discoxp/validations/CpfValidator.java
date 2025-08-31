package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.repository.CpfHolderRepository;

public interface CpfValidator {
    CpfHolderRepository getCpfHolderRepository();

    default void validateCpf(String cpf) {
        System.out.println(getCpfHolderRepository().existsByCpf(cpf));
    }
}
