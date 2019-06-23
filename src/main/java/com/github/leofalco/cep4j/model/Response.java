package com.github.leofalco.cep4j.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class Response {
    private final String status;
    private final String content;
    private final String contentType;


    public Response(int status, String contentType, @NonNull String content) {
        this.status = String.valueOf(status);
        this.contentType = contentType;
        this.content = content;
    }
}
