package com.github.leofalco.cep4j;

import java.security.InvalidParameterException;
import java.util.Objects;

public class Strings {
    private Strings() {
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

    public static String onlyDigitsAndNotEmpty(String s) {
        if (s == null)
            throw new InvalidParameterException();

        s = s.replaceAll("\\D", "");

        if (s.isEmpty()) {
            throw new InvalidParameterException();
        }

        return s;
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

    public static String notEmpty(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new InvalidParameterException();
        }

        return s;
    }
}
