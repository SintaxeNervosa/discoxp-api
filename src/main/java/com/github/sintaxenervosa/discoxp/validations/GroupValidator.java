package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Group;

public interface GroupValidator {
    default void validateGroup(String group) {
        if(group == null) {
            UserValidator.addErroMessageInList("Informe o grupo");
            return;
        }

        Group stringToGroup;

        try {
            stringToGroup = Group.valueOf(group);
        } catch (IllegalArgumentException e) {
            UserValidator.addErroMessageInList("Grupo inválido");
            return;
        }

        boolean exists = false;

        for(Group g : Group.values()) {
            if(g.name().equals(stringToGroup.name())) {
                exists = true;
            }
        }

        if(!exists) { UserValidator.addErroMessageInList("Grupo Inexistente"); }
    }
}
