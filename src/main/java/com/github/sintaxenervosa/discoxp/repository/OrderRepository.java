package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.model.Order;
import com.github.sintaxenervosa.discoxp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllOrdersByUser(User user);

}
