package me.leo.cepj4.core;

import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.postmon.PostmonResolver;
import me.leo.cepj4.model.CepResponse;
import org.junit.Test;

import java.util.concurrent.CompletionException;

public class PostmonResolverTest {

    @Test
    public void successTest() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        CepResponse cep = cep4j.fetch("15110000").join();
        System.out.println("Resultado: " + Json.toJson(cep));
    }

    @Test
    public void failWenCepNotExists() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        try {
            cep4j.fetch("15110040").join();
        } catch (CompletionException e) {
            System.out.println("Resultado: " + e.getCause().getMessage());
        }
    }

}
