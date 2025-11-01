package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
};
