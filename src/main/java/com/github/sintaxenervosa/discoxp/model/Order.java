package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private OrderStatus orderStatus =  OrderStatus.AWAITING_PAYMENT;

    private LocalDate orderDate = LocalDate.now();

    @Column(nullable = false)
    private BigDecimal freight;

    private BigDecimal totalOrderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private DeliveryAddress deliveryAddress;

    public Order(PaymentMethod paymentMethod, BigDecimal freight, User user, DeliveryAddress deliveryAddress) {
        this.paymentMethod = paymentMethod;
        this.freight = freight;
        this.user = user;
        this.deliveryAddress = deliveryAddress;
    }

    public Order() { }
}
