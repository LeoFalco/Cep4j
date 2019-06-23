package com.github.leofalco.cep4j.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.Map;

@Getter
@ToString
public class ResponseMap {

    private final String status;
    private Map<String, Object> map;

    public ResponseMap(String status, Map<String, Object> map) {
        this.status = status;
        this.map = Collections.unmodifiableMap(map);
    }
}