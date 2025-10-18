package com.github.sintaxenervosa.discoxp.model;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
@Entity()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String password;

    private boolean status = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Group groupEnum;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String name, String email, String password, Group groupEnum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupEnum = groupEnum;
    }

    // estoquista && admin
    public User(CreateUserRequestDTO userRequest) {
        this.name = userRequest.name();
        this.email = userRequest.email();
        this.password = userRequest.password();
        this.cpf = userRequest.cpf();
        this.groupEnum = Group.valueOf(userRequest.group());
    }

    // client
    public User(String name, String email, String password, Group groupEnum, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupEnum = groupEnum;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
