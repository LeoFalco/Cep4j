package me.leo.cepj4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class Http {
    public static String get(String uri) throws IOException {
        URL url = new URL(uri);
        try (InputStream stream = url.openStream()) {
            return new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
        }
    }
}
