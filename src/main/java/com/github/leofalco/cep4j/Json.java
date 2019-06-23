package com.github.leofalco.cep4j;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Json {
    private static final ObjectMapper MAPPER;

    static {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        MAPPER = mapper;
    }


    public static String stringfy(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter objeto para json", e);
        }
    }

    public static <T> T parse(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter json para objeto", e);
        }
    }

    public static <T> T parse(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter json para objeto", e);
        }
    }

    public static Map<String, Object> toMap(String json) {
        if(json.isEmpty()){
            return new HashMap<>();
        }

        return parse(json, MapType.TYPE);
    }

    private static class MapType extends TypeReference<Map<String, Object>> {
        static final MapType TYPE = new MapType();
    }
}
