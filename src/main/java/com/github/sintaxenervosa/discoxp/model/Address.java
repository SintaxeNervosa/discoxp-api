package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_address")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Column(nullable = false)
    private boolean enderecoPadrao = false;
}
