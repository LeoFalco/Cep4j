package me.leo.cepj4.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ManyRuntimeException extends RuntimeException {

    private final List<Throwable> throwableList;

    public ManyRuntimeException(List<Throwable> throwableList) {
        this.throwableList = throwableList;
    }
}
