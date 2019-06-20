package me.leo.cepj4.model;

import lombok.Getter;
import lombok.ToString;
import me.leo.cepj4.Json;

import java.util.Collections;
import java.util.Map;

@Getter
@ToString
public class Response {
    private final String status;
    private final String content;
    private Map<String, Object> map;

    public Response(int status, String content) {
        this.status = Integer.toString(status);
        this.content = content;
        this.map = Collections.unmodifiableMap(Json.toMap(this.content));
    }
}
