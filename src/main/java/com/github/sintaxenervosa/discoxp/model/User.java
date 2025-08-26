package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    private boolean status = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Group groupEnum;
}
