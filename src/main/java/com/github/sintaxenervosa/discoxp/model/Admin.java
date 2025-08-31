package com.github.sintaxenervosa.discoxp.model;

import com.github.sintaxenervosa.discoxp.dto.AdminRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Table(name = "tb_admin")
@AllArgsConstructor
@Entity()
public class Admin extends User {

    public Admin(AdminRequestDto admin) {
        super(admin.name(), admin.email(), admin.password());
    }
}
