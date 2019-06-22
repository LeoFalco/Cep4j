package com.github.leofalco.cep4j.exceptions;

import lombok.Getter;

import static com.github.leofalco.cep4j.Operator.elvis;

@Getter
public class ServiceException extends RuntimeException {

    private final String serviceName;
    private final String code;
    private final String message;
    private final String description;

    public ServiceException(String serviceName, String code, String message, String description) {
        super(String.join(", ", "\n\t", serviceName, code, message, description));
        this.serviceName = serviceName;
        this.code = code;
        this.message = message;
        this.description = description;

    }

    public static ServiceException ofException(String serviceName, Exception e) {
        throw new ServiceException(serviceName,
                elvis(() -> e.getClass().getSimpleName()),
                elvis(e::getMessage),
                elvis(() -> e.getCause().getMessage())
        );
    }
}
