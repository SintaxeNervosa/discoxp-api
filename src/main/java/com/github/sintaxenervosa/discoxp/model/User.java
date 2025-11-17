package com.github.sintaxenervosa.discoxp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sintaxenervosa.discoxp.dto.client.CreateClientRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
@Entity
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

    @OneToOne(cascade = CascadeType.REMOVE, fetch =  FetchType.LAZY)
    @JsonIgnore
    private BillingAddress billingAddress;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Order> orders;

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
        this.dateOfBirth = userRequest.dateOfBirth();
    }

    public User(CreateClientRequestDTO clientRequestDTO) {
        this.name = clientRequestDTO.name();
        this.email = clientRequestDTO.email();
        this.password = clientRequestDTO.password();
        this.cpf = clientRequestDTO.cpf();
        this.groupEnum = Group.CLIENT;
    }

    // client
    public User(String name, String cpf, String email, String password, Group groupEnum, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.groupEnum = groupEnum;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    // client
    public User(Long id, String name, String email, String password, Group groupEnum, LocalDate dateOfBirth, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupEnum = groupEnum;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
