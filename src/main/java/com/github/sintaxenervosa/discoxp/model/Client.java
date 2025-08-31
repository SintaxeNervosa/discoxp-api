package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_client")
public class Client extends User {

    public Client(String name, String email, String password, Group group) {
        super(name, email, password);

    }

    public Client(Long id, String name, String email, String password, boolean status, Group groupEnum, Group group) {
        super(id, name, email, password, status, groupEnum);

    }

    public Client(Group group) {
    }

    public Client() {

    }
}
