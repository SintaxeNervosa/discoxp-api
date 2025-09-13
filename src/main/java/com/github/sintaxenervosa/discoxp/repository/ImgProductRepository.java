package com.github.sintaxenervosa.discoxp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.model.Product;

@Repository
public interface  ImgProductRepository extends JpaRepository<ImageProduct, Long>{
    List<ImageProduct> findByProduct_Id(Long productId);
    List<ImageProduct> findByProduct(Product product);
}
