package me.leo.cepj4.core;

public class Util {
    private Util() {
        throw new IllegalStateException("Utility class");
    }


    private static String removeSpecialCharacters(String cep) {
        return cep.replaceAll("^[0-9]", "");
    }

    private static String leftPadWithZero(String unpadded) {
        return "00000000".substring(unpadded.length()).concat(unpadded);
    }

    private static void validateInputLenght(String normalizedCep) {
        if (normalizedCep.length() >= 8) {
            throw new RuntimeException();
        }
    }

    public static String tratarInput(String input) {
        input = removeSpecialCharacters(input);
        validateInputLenght(input);
        return leftPadWithZero(input);
    }

}
