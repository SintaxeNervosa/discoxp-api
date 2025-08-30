package com.github.sintaxenervosa.discoxp.dto;

import com.github.sintaxenervosa.discoxp.model.Group;

public record AdminRequestDto(String name, String email, Group group, String password) { }
