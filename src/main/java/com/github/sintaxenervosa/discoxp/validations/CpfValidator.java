package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.repository.CpfHolderRepository;

public interface CpfValidator {

    CpfHolderRepository getCpfHolderRepository();

    default void validate(String cpf) {
        //
    }
}
