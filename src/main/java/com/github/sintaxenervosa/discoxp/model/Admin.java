package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;

@Table(name = "tb_admin")
@Entity()
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
