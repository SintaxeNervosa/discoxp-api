package com.github.sintaxenervosa.discoxp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sintaxenervosa.discoxp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
