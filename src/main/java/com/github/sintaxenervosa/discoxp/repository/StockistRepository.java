package com.github.sintaxenervosa.discoxp.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.sintaxenervosa.discoxp.model.Stockist;

@Primary
public interface StockistRepository extends JpaRepository<Stockist, Long>, CpfHolderRepository {
    boolean existsByCpf(String cpf);
}
