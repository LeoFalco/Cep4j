package me.leo.cepj4;

import java.util.function.Supplier;

public class Operator {

    public static <T> T elvis(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
