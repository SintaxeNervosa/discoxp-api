package com.github.sintaxenervosa.discoxp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sintaxenervosa.discoxp.model.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
}
