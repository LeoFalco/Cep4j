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
        this(serviceName, code, message, description, null);
    }

    public ServiceException(String serviceName, String code, String message, String description, Exception cause) {
        super(String.join(", ", "\n\t", serviceName, code, message, description), cause);
        this.serviceName = serviceName;
        this.code = code;
        this.message = message;
        this.description = description;

    }

    public static ServiceException ofException(String serviceName, Exception cause) {
        throw new ServiceException(serviceName,
                elvis(() -> cause.getClass().getSimpleName()),
                elvis(cause::getMessage),
                elvis(() -> cause.getCause().getMessage()),
                cause
        );
    }
}
