package com.github.leofalco.cep4j.exceptions;

import lombok.Getter;

import static com.github.leofalco.cep4j.Operator.elvis;

@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -3222751865901759943L;

    private final String serviceName;
    private final String code;
    private final String mensagem;
    private final String description;

    public ServiceException(String serviceName, String code, String mensagem, String description) {
        this(serviceName, code, mensagem, description, null);
    }

    public ServiceException(String serviceName, String code, String mensagem, String description, Exception cause) {
        super(String.join("",
                "service=",
                serviceName, " code=", code,
                " mensagem=", mensagem,
                " description=", description
                ),
                cause);
        this.serviceName = serviceName;
        this.code = code;
        this.mensagem = mensagem;
        this.description = description;

    }

    public ServiceException(String serviceName, Exception cause) {
        this(serviceName,
                elvis(() -> cause.getClass().getSimpleName()),
                elvis(cause::getMessage),
                elvis(() -> cause.getCause().getMessage()), cause);
    }
}
