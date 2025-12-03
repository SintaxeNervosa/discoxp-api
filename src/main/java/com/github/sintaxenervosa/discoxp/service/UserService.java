package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import com.github.sintaxenervosa.discoxp.dto.client.ExistsCpfResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.client.ExistsEmailResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.user.*;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.Gender;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

import com.github.sintaxenervosa.discoxp.validations.user.DefaultUserValidator;
import io.micrometer.common.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final DefaultUserValidator userValidator;
    private final PasswordEncoder  passwordEncoder;
    private final DefaultUserValidator defaultUserValidator;
    private final ViaCepService viaCepService;

    public UserService(DefaultUserValidator userValidator, UserRepository userRepository, PasswordEncoder passwordEncoder, DefaultUserValidator defaultUserValidator, ViaCepService viaCepService){
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultUserValidator = defaultUserValidator;
        this.viaCepService = viaCepService;
    }

    @Transactional
    public CreateUserResponseDTO createUser(CreateUserRequestDTO request, @Nullable RequestAddressDTO deliveryAddress, @Nullable RequestAddressDTO billingAddress) {
        userValidator.validateUserCreation(request);

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request);
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);

        if(request.group().equals(Group.CLIENT.toString())) {
            RequestAddressDTO newDeliveryAddress = new RequestAddressDTO(
                    savedUser.getId().toString(),
                    deliveryAddress.cep(),
                    deliveryAddress.number(),
                    deliveryAddress.complement());

            RequestAddressDTO newBillingAddress = new RequestAddressDTO(
                    savedUser.getId().toString(),
                    billingAddress.cep(),
                    billingAddress.number(),
                    billingAddress.complement());

            viaCepService.fyndCepFromDelivery(newDeliveryAddress);
            viaCepService.findByCepFromAddress(newBillingAddress);
        }

        return CreateUserResponseDTO.fromEntity(savedUser);
    }

    public LoginResponseDto loginUser(LoginRequestDto request) {
        userValidator.validateLogin(request);

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean validPassword = passwordEncoder.matches(request.password(), user.getPassword());
        if (!validPassword) {
            throw new RuntimeException("Email e/ou senha inválidos");
        }

        return LoginResponseDto.fromEntity(user);
    }

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
        if(request.password() == "") {
            newUser.setPassword(savedUser.getPassword());
        } else {
            newUser.setPassword(passwordEncoder.encode(request.password()));
        }

        newUser.setName(request.name());
        newUser.setCpf(request.cpf());
        newUser.setGroupEnum(Group.valueOf(request.group()));
        newUser.setBillingAddress(savedUser.getBillingAddress());
        newUser.setDeliveryAddresses(savedUser.getDeliveryAddresses());

        if(newUser.getGroupEnum().equals(Group.CLIENT)) {
            newUser.setDateOfBirth(request.dateOfBirth());
            newUser.setGender(Gender.valueOf(request.gender()));
            System.out.println("CLIENTE");
        } else {
            newUser.setDateOfBirth(savedUser.getDateOfBirth());
            newUser.setGender(savedUser.getGender());
            System.out.println("NÃO CLIENTE");
        }

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