package com.github.leofalco.cep4j;

import java.util.Objects;

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


    public static boolean contains(String a, String b) {
        if (Objects.equals(a, b))
            return true;

        return a.contains(b);
    }


    public static boolean containsIgnoreCase(String a, String b) {
        if (Objects.equals(a, b))
            return true;

        return contains(a.toLowerCase(), b.toLowerCase());
    }
}
