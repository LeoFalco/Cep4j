package com.github.leofalco.cep4j.model;

import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

@Getter
public class ResponseMap {

    private final String status;
    private final Map<String, ?> map;

    public ResponseMap(final String status, final Map<String, ?> map) {
        this.status = status;
        this.map = Collections.unmodifiableMap(map);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final String path) {
        Object temp = map;
        final String[] split = path.split("\\.");

        final int length = split.length;
        final int maxIndex = length - 1;
        for (int i = 0; i < length; i++) {
            final String key = split[i];

            if (temp instanceof Map) {
                // noinspection unchecked
                temp = ((Map<String, ?>) temp).get(key);
            } else {
                temp = getField(temp, key);
            }

            if (i != maxIndex && temp == null) {
                throw new NullPointerException("Null point when try to use: " + key);
            }
        }
        // noinspection unchecked
        return (T) temp;

    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static <T> T getField(final Object obj, final String fieldName) {
        final Field[] fields = obj.getClass().getFields();

        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);            
                // noinspection unchecked
                return (T) field.get(obj);

            }
        }
        
        throw new IllegalStateException("Campo não encontrado: " + fieldName);
    }


}
