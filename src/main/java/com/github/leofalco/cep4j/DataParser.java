package com.github.leofalco.cep4j;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

public interface DataParser {

    String stringfy(Object object);

    <T> T parse(String json, Class<T> type);

    <T> T parse(String json, TypeReference<T> type);

    Map<String, Object> toMap(String json);

    <T> T convert(Object o, Class<T> type);

}
