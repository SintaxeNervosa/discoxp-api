package com.github.sintaxenervosa.discoxp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.sintaxenervosa.discoxp.dto.product.UpdateProductRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "tb_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    private double evaluation;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @Column(name="quantity",nullable = false)
    private Integer quantity;

    private boolean status = true;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageProduct> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;

    // construtor para atualizar o produto
    public Product(UpdateProductRequestDTO product) {
        this.name = product.name();
        this.evaluation = product.evaluation();
        this.description = product.description();
        this.price = product.price();
        this.quantity = product.quantity();
    }

    public Product(Long id, String name, double evaluation, String description, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.evaluation = evaluation;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, double evaluation, String description, BigDecimal price, Integer quantity) {
        this.name = name;
        this.evaluation = evaluation;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() { }
}
