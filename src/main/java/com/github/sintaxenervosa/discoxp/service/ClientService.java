package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.validations.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public void changeClient(UpdateUserRequestDTO request) {
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

        userValidator.validateUserUpdate(savedUser, request);
    }
}
