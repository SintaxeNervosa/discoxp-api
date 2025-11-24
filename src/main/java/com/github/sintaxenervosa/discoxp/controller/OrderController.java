package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.order.ChangeOrderStatusRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.CreateOrderResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderResponseDTO;
import com.github.sintaxenervosa.discoxp.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> addOrder(@RequestBody OrderRequestDTO request) {
        CreateOrderResponseDTO orderResponse = orderService.addOrder(request);
        return ResponseEntity.ok(orderResponse);
    };

    @PutMapping("{id}")
    public ResponseEntity<HttpStatusCode> updateOrderStatus(@PathVariable String id, @RequestBody ChangeOrderStatusRequestDTO request) {
        orderService.changeOrderStatus(request, id);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<List<OrderResponseDTO>> findAllOrdersByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(orderService.findAllOrdersByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }
}
