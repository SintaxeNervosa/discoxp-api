package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
