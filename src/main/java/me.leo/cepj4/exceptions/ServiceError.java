package me.leo.cepj4.exceptions;

import lombok.Getter;
import me.leo.cepj4.Operator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class ServiceError extends RuntimeException {

    private final Content content;

    public ServiceError(String serviceName, String code, String message, String description) {
        super(Stream.of(serviceName, code, message, description).collect(Collectors.joining(", ", "\n\t", "")));
        this.content = new Content(serviceName, code, message, description);
    }

    public static ServiceError ofException(String serviceName, Exception e) {
        throw new ServiceError(serviceName,
                Operator.elvis(() -> e.getClass().getSimpleName()),
                Operator.elvis(e::getMessage),
                Operator.elvis(() -> e.getCause().getMessage())
        );
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
