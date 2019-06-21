package com.github.leofalco.cep4j;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Json implements DataParser {
    private static final ObjectMapper MAPPER;

    static {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        MAPPER = mapper;
    }


    public String stringfy(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter objeto para json", e);
        }
    }

    public <T> T parse(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter json para objeto", e);
        }
    }

    public <T> T parse(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter json para objeto", e);
        }
    }

    public Map<String, Object> toMap(String json) {
        if(json.isEmpty()){
            return new HashMap<>();
        }

        return parse(json, new TypeReference<Map<String, Object>>() {
        });
    }

    public <T> T convert(Object o, Class<T> type) {
        return MAPPER.convertValue(o, type);
    }
}
