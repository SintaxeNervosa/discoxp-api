package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderResponseDTO;
import com.github.sintaxenervosa.discoxp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.addOrder(orderRequestDTO);
        return ResponseEntity.ok().build();
    };

    @GetMapping("{id}")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(orderService.findAllOrdersByUser(userId));
    }
}
