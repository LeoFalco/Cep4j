package com.github.leofalco.cep4j;

import java.util.function.Supplier;

public class Operator {
    private Operator(){
    }

    public static <T> T elvis(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
