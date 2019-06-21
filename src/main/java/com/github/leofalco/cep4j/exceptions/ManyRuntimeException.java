package com.github.leofalco.cep4j.exceptions;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ManyRuntimeException extends RuntimeException {

    private final List<Throwable> throwableList;

    public ManyRuntimeException(List<Throwable> throwableList) {
        super(throwableList.stream().map(Throwable::getMessage).collect(Collectors.joining(", ")));
        this.throwableList = throwableList;
    }
}
