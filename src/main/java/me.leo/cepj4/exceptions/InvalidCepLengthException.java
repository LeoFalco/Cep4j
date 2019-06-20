package me.leo.cepj4.exceptions;

public class InvalidCepLengthException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "O Cep %s não contém 8 caracteres.";

    public InvalidCepLengthException(String cep) {
        super(String.format(MESSAGE_TEMPLATE, cep));
    }
}
