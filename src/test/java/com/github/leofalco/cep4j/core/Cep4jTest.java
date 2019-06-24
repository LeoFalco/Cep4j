package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolver.ResolverTest;
import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import com.sun.net.httpserver.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class Cep4jTest implements ResolverTest {

    private static Cep4j cep4j;
    private static ClientAndServer clientAndServer;

    @BeforeClass
    public static void setup() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext("/api/endpoint", exchange -> {
            byte[] response = "{\"success\": true}".getBytes();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });


        httpServer.start();
        cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());

        httpServer.stop(0);
    }

    @AfterClass
    public static void shutdown() {
        //clientAndServer.stop();
    }

    @Test
    @Override
    public void fetchGuapiacu() {
        CompletableFuture<CepResponse> fetch = cep4j.fetchAsync("15110-000");
        CepResponse cep = fetch.join();
        assertThat(cep.getCep()).isEqualTo("15110000");
        assertThat(cep.getCidade()).isEqualTo("Guapiaçu");
    }

    @Test
    @Override
    public void fetchRuaAuriflama() {

    }

    @Test
    @Override
    public void fetchEngenheiroBalduino() {

    }

    @Test
    @Override
    public void failWenCepNotExistsWithCorrectMessages() {

    }
}