package com.github.leofalco.cep4j.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Response {
    private final String status;
    private final String content;

    public Response(int status, String content) {
        this.status = String.valueOf(status);
        this.content = content;
    }
}
