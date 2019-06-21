package com.github.leofalco.cep4j;

import com.github.leofalco.cep4j.model.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class Http {
    private Http() {
    }

    private static final HttpClient client = HttpClientBuilder.create().build();

    public static Response get(String uri) throws IOException {
        System.out.println("uri = " + uri);
        HttpResponse httpResponse = client.execute(new HttpGet(uri));

        try (InputStream content = httpResponse.getEntity().getContent()) {

            int statusCode = readStatusCode(httpResponse);
            System.out.println("statusCode = " + statusCode);

            String contentAsString = readResponse(content);
            return new Response(statusCode, contentAsString);
        }
    }

    public static Response post(String uri, String body, Map<String, String> headers) throws IOException {
        System.out.println("uri = " + uri);
        HttpPost post = new HttpPost(uri);
        headers.forEach(post::addHeader);
        post.setEntity(new StringEntity(body));

        HttpResponse response = client.execute(post);
        try (InputStream responseAsStream = response.getEntity().getContent()) {
            int statusCode = readStatusCode(response);
            System.out.println("statusCode = " + statusCode);
            String responseAsString = readResponse(responseAsStream);

            return new Response(statusCode, responseAsString);
        }

    }

    private static String readResponse(InputStream response) {
        return new BufferedReader(new InputStreamReader(response))
                .lines()
                .collect(Collectors.joining());

    }

    public static int readStatusCode(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }
}
