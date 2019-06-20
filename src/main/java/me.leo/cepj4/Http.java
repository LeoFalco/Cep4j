package me.leo.cepj4;

import me.leo.cepj4.model.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Http {
    private Http() {
    }

    private static final HttpClient client = HttpClientBuilder.create().build();

    public static Response get(String uri) throws IOException {
        System.out.println("uri = " + uri);
        HttpResponse httpResponse = client.execute(new HttpGet(uri));
        try (InputStream content = httpResponse.getEntity().getContent()) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("statusCode = " + statusCode);
            String contentAsString = new BufferedReader(new InputStreamReader(content))
                    .lines()
                    .collect(Collectors.joining());

            System.out.println("contentAsString = " + contentAsString);
            return new Response(statusCode, contentAsString);
        }
    }
}
