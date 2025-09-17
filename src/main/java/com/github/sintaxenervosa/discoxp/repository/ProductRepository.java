package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long aLong);
    boolean existsById(Long aLong);
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
}
