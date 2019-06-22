package com.github.leofalco.cep4j;

public class Strings {
    private Strings() {
        throw new IllegalStateException("Utility class");
    }
    public static String emptyToNull(String s) {
        if (s == null || s.isEmpty())
            return null;
        return s;
    }

    public static String onlyDigits(String s) {
        if (s == null)
            return null;

        return s.replaceAll("\\D", "");
    }
}
