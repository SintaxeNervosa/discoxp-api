package com.github.sintaxenervosa.discoxp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sintaxenervosa.discoxp.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    boolean existsByEmail(String email);
} 
