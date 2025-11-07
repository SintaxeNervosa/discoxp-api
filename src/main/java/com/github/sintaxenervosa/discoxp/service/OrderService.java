package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.CreateOrderResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.order.orderItem.OrderItemResponseDTO;
import com.github.sintaxenervosa.discoxp.exception.order.InvalidOrderException;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.*;
import com.github.sintaxenervosa.discoxp.repository.*;
import com.github.sintaxenervosa.discoxp.validations.order.DefaultOrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository  orderItemRepository;
    private final DefaultOrderValidator defaultOrderValidator;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Transactional
    public CreateOrderResponseDTO addOrder(OrderRequestDTO request) {
        defaultOrderValidator.validateAddOrder(request); // validações de formulário

        // busca o id do usuário
        User user = userRepository
                .findById(Long.parseLong(request.userId()))
                .orElseThrow(() -> new UserNotFoundExeption("Usuário não encontrado."));

        // busca os produtos
        Optional<Product> productOpt;
        for(ProductAndQuantityRequestDTO p : request.products()) {
            productOpt = productRepository.findById(Long.parseLong(p.productId()));

            // valida a quantidade em estoque
            if(productOpt.isPresent()) {
                if(Integer.parseInt(p.quantity()) > productOpt.get().getQuantity()) {
                    throw new InvalidOrderException("Quantidade indisponível.");
                }
            }
        }

        // criar o pedido
        Order order = new Order(
                PaymentMethod.valueOf(request.paymentMethod()),
                BigDecimal.valueOf(Double.parseDouble(request.freight())),
                user,
                deliveryAddressRepository.findByUserAndIsFavoriteTrue(user)
        );

        // faz a persistência do pedido
        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        Product temp;
        int quantity;

        // adiciona o itens no pedido
        for(ProductAndQuantityRequestDTO p : request.products()) {
            temp = productRepository.findById(Long.parseLong(p.productId())).get();
            OrderItem orderItem = new OrderItem(
                    quantity = Integer.parseInt(p.quantity()),
                    totalOrderItem(temp, quantity),
                    order,
                    temp
            );

            // salva o item_pedido e o adiciona na lista
            orderItems.add(orderItemRepository.save(orderItem));

            // subtrai a quantidade do produto em estoque
            temp.setQuantity(temp.getQuantity() - orderItem.getQuantity());
            productRepository.save(temp);
        }

        // colocar os itens no pedido
        order.setOrderItems(orderItems);

        // calcula o total do pedido
        order.setTotalOrderItems(totalOrder(order, orderItems));
        System.out.println(order.getTotalOrderItems());


        return CreateOrderResponseDTO.fromEntity(orderRepository.save(order));
    }


    public BigDecimal totalOrderItem(Product product, Integer quantity) {
        return BigDecimal.valueOf(product.getPrice().doubleValue() * quantity);
    }

    public BigDecimal totalOrder(Order order, List<OrderItem> orderItems) {
        BigDecimal totalOrder = order.getFreight();

        for(OrderItem orderItem : orderItems) {
            totalOrder = totalOrder.add(orderItem.getTotal());
        }

        return totalOrder;
    }

    public List<OrderResponseDTO> findAllOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserDataException("Usuário não encontrado."));

        List<Order> orderList = orderRepository.findAllOrdersByUser(user);

        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        List<OrderItemResponseDTO> orderItemResponseDTOList;

        for(Order order : orderList) {
            orderItemResponseDTOList = order.getOrderItems()
                     .stream()
                     .map(OrderItemResponseDTO::fromEntity).toList();

            orderResponseDTOList.add(new OrderResponseDTO(
                    order.getOrderId(),
                    order.getOrderDate(),
                    order.getOrderStatus(),
                    order.getTotalOrderItems(),
                    orderItemResponseDTOList));
        }

        return orderResponseDTOList;
    }
}
