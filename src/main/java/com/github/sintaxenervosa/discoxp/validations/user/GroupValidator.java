package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface GroupValidator {
    default void validateGroup(String group) {
        if(group == null) {
            ValidationErrorRegistry.addError("Informe o grupo");
            return;
        }

        Group stringToGroup;

        try {
            stringToGroup = Group.valueOf(group);
        } catch (IllegalArgumentException e) {
            ValidationErrorRegistry.addError("Grupo inválido");
            return;
        }

        boolean exists = false;

        for(Group g : Group.values()) {
            if(g.name().equals(stringToGroup.name())) {
                exists = true;
            }
        }

        if(!exists) { ValidationErrorRegistry.addError("Grupo Inexistente"); }
    }
}
