package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_billing_address")
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9 , nullable = false)
    private String cep;

    @Column(length = 60 , nullable = false)
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
    private String estado;

    @OneToOne
    @JoinColumn(name = "billingAddress")
    private User user;
}
