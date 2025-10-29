package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.repository.OrderRepository;
import com.github.sintaxenervosa.discoxp.validations.order.DefaultOrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DefaultOrderValidator defaultOrderValidator;

    public void addOrder(OrderRequestDTO request) {
        defaultOrderValidator.validateAddOrder(request);

        // buscar o id do usuário

        // buscar os produtos
        // verificar se tem a quantidade em estoque


        // buscar o id do endereço (se informado)

        // criar o order_item


    }
}
