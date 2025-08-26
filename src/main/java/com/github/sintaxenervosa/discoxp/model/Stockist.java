package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;

@Table(name = "tb_stockist")
@Entity()
public class Stockist extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 8)
    private String cpf;
}
