package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.client.ExistsCpfResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.client.ExistsEmailResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

import com.github.sintaxenervosa.discoxp.validations.user.DefaultUserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.sintaxenervosa.discoxp.dto.user.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.LoginResponseDto;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final DefaultUserValidator userValidator;
    private final PasswordEncoder  passwordEncoder;
    private final DefaultUserValidator defaultUserValidator;

    public UserService(DefaultUserValidator userValidator, UserRepository userRepository, PasswordEncoder passwordEncoder, DefaultUserValidator defaultUserValidator){
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultUserValidator = defaultUserValidator;
    }

    // TIPO UUS
    public void createUser(CreateUserRequestDTO request) {
        userValidator.validateUserCreation(request);
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request);

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public LoginResponseDto loginUser(LoginRequestDto request){
        userValidator.validateLogin(request);

        User user = userRepository.findByEmail(request.email()).get();
        return LoginResponseDto.fromEntity(user);
    }

    //Listar todos os usuários
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //buscar por id
    public Optional<User> findUserById(String id) {
        defaultUserValidator.validateUserSearchById(id); // valida o id informado
        return userRepository.findById(Long.parseLong(id));
    }

    public void updateUser(UpdateUserRequestDTO request) {
        if(request.id() == null || request.id().isEmpty()) {
            throw new InvalidUserDataException("Informe o id");
        }

        long id = 0;

        try {
            id = Long.parseLong(request.id());
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido");
        }

        User savedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeption("Usuário não encontrado"));

        userValidator.validateUserUpdate(savedUser, request); // realiza todas as validações para editar

        User newUser = new User();
        // dados que não se alteram
        newUser.setId(savedUser.getId());
        newUser.setEmail(savedUser.getEmail());

        // dados que se alteram
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setName(request.name());
        newUser.setCpf(request.cpf());
        newUser.setGroupEnum(Group.valueOf(request.group()));

        userRepository.save(newUser);
    }

    public void changeStatus(String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        user.setStatus(!user.isStatus());
        userRepository.save(user);
    }

    public ExistsEmailResponseDTO emailExists(String email) {
        return ExistsEmailResponseDTO.fromEntity(userRepository.existsByEmail(email));
    }

    public ExistsCpfResponseDTO cpfExists(String cpf) {
        return ExistsCpfResponseDTO.fromEntity(userRepository.existsByCpf(cpf));
    }
}