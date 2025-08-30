package com.github.sintaxenervosa.discoxp.model;

import com.github.sintaxenervosa.discoxp.dto.AdminRequestDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Table(name = "tb_admin")
@Entity()
@NoArgsConstructor
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Admin(AdminRequestDto admin) {
        super(admin.name(), admin.email(), admin.password(), admin.group());
    }

    public Admin(Admin admin) {
        super(admin.getName(), admin.getName(), admin.getPassword(), admin.getGroupEnum());
    }

}
