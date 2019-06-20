package me.leo.cepj4.exceptions;

import lombok.Getter;

@Getter
public class ServiceError extends RuntimeException {
    private final String serviceName;
    private final String code;
    private final String message;
    private final String description;

    public ServiceError(String code, String message, String description, String serviceName) {
        super(message);
        this.serviceName = serviceName;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public static ServiceError ofException(String serviceName, Exception e) {
        System.out.println("e.getClass().getSimpleName() = " + e.getClass().getSimpleName());
        System.out.println("e.getMessage() = " + e.getMessage());
        System.out.println("e.getCause().getMessage() = " + e.getCause().getMessage());
        System.out.println("serviceName = " + serviceName);

        throw new ServiceError(serviceName, e.getClass().getSimpleName(), e.getMessage(), e.getCause().getMessage());

    }
}
