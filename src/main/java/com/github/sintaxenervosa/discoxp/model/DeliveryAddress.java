
package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "tb_delivery_address")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9, nullable = false)
    private String cep;

    @Column(length = 60, nullable = false)
    private String street;

    @Column(length = 6)
    private String number;

    @Column(length = 150)
    private String complement;

    @Column(length = 60, nullable = false)
    private String neighborhood;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 2, nullable = false)
    private String uf;

    private boolean isFavorite = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<OrderItem> orderItems;

    public DeliveryAddress(Long id, String uf, String city, String neighborhood, String complement, String number, String street, String cep) {
        this.id = id;
        this.uf = uf;
        this.city = city;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.number = number;
        this.street = street;
        this.cep = cep;
    }

    public DeliveryAddress(String uf, String city, String neighborhood, String complement, String number, String street, String cep) {
        this.uf = uf;
        this.city = city;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.number = number;
        this.street = street;
        this.cep = cep;
    }

    public DeliveryAddress() {}
}

