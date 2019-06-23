package com.github.leofalco.cep4j;

import com.github.leofalco.cep4j.exceptions.ServiceException;
import lombok.NonNull;

public class Validator {
    private Validator() {
    }


    private static String removeSpecialCharacters(@NonNull String cep) {
        return cep.replaceAll("\\D", "");
    }

    private static String validateInput(String normalizedCep) {
        if (normalizedCep.length() != 8) {
            throw new ServiceException("Validator", "invalid_input", "Cep inválido", "Cep deve conter 8 caracteres.");
        }
        return normalizedCep;
    }

    public static String tratarInput(String input) {
        return validateInput(removeSpecialCharacters(input));
    }

}
