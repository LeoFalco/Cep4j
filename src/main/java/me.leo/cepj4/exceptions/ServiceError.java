package me.leo.cepj4.exceptions;

import lombok.Getter;

@Getter
public class ServiceError extends RuntimeException {

    private final Content content;

    public ServiceError(String code, String message, String description, String serviceName) {
        super(message);
        this.content = new Content(serviceName, code, message, description);
    }

    public static ServiceError ofException(String serviceName, Exception e) {
        throw new ServiceError(serviceName, e.getClass().getSimpleName(), e.getMessage(), e.getCause().getMessage());
    }


    @Getter
    public class Content {
        private final String serviceName;
        private final String code;
        private final String message;
        private final String description;

        public Content(String serviceName, String code, String message, String description) {
            this.serviceName = serviceName;
            this.code = code;
            this.message = message;
            this.description = description;
        }
    }
}
