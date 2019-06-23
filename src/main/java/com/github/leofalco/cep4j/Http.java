package com.github.leofalco.cep4j;

import com.github.leofalco.cep4j.model.Response;
import org.apache.http.Header;
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
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Http {
    public static final Logger log = Logger.getLogger(Http.class.getName());
    private Http() {
    }

    private static final HttpClient client = HttpClientBuilder.create().build();

    public static Response get(String uri) throws IOException {
        log.info("uri: " + uri);
        HttpResponse httpResponse = client.execute(new HttpGet(uri));
        return readResponse(httpResponse);
    }

    public static Response post(String uri, String body, Map<String, String> headers) throws IOException {
        log.info("uri: " + uri);
        HttpPost post = new HttpPost(uri);
        headers.forEach(post::addHeader);
        post.setEntity(new StringEntity(body));

        HttpResponse httpResponse = client.execute(post);
        return readResponse(httpResponse);
    }

    private static Response readResponse(HttpResponse httpResponse) throws IOException {
        try (InputStream responseAsStream = httpResponse.getEntity().getContent()) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(responseAsStream, StandardCharsets.UTF_8)) {
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    log.info("status code: " + statusCode);
                    String responseAsString = bufferedReader.lines().collect(Collectors.joining());
                    Header contentType = httpResponse.getEntity().getContentType();
                    System.out.println("responseAsString = " + responseAsString);
                    return new Response(statusCode, contentType, responseAsString);
                }
            }
        }
    }
}
