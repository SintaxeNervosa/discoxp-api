package com.github.sintaxenervosa.discoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends User {
    private final Group group = Group.CLIENT;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    public Client(String name, String email, String password) {
        super( name, email, password);
    }
    
    public Group getGroup() {
        return group;
    }
}