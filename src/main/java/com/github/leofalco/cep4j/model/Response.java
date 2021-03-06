package com.github.leofalco.cep4j.model;

import lombok.Getter;
import org.apache.http.Header;

@Getter
public class Response {
    private final String status;
    private final String content;
    private final Header contentType;

    public Response(int status, Header contentType, String content) {
        this.status = String.valueOf(status);
        this.contentType = contentType;
        this.content = content;
    }
}
