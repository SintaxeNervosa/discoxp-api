package com.github.sintaxenervosa.discoxp.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CpfHolderRepository {
    boolean existsByCpf(String cpf);
}
