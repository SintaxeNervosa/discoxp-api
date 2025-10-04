package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findById(Long aLong);
    boolean existsById(Long aLong);
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
    Page<Product> findAllByOrderByIdDesc(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseOrderByIdDesc(String nome, Pageable pageable);

    Long id(Long id);
}
