package com.github.sintaxenervosa.discoxp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.StockistRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    private StockistRepository stockistRepository;

    public Optional<User> login(LoginRequestDto loginRequest){
        System.out.println("Vamos lá logando...");
        return null;
    }

    //Listar todos os usuários
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //buscar por id
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    //Incluir usuário
    public User saveUser(User user){
        return userRepository.save(user);
    }

    //Alternar usuário
    public User updateUser(Long id, User dados) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(dados.getName());
                    user.setEmail(dados.getEmail());
                    user.setPassword(dados.getPassword());
                    user.setGroupEnum(dados.getGroupEnum());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

    }

    //Habilitar ou desabilitar usuário
    public User statsUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setStatus(!user.isStatus());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

}