package me.leo.cepj4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Xml implements DataParser {
    private static final XmlMapper MAPPER;

    static {

        XmlMapper mapper = new XmlMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        MAPPER = mapper;
    }

    @Override
    public String stringfy(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parse(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parse(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> toMap(String json) {
        if (json.isEmpty()) {
            return new HashMap<>();
        }

        return parse(json, new TypeReference<Map<String, Object>>() {
        });

    }

    @Override
    public <T> T convert(Object o, Class<T> type) {
        try {
            return MAPPER.convertValue(o, type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
