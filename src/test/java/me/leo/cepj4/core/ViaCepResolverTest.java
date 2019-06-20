package me.leo.cepj4.core;

import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.ViaCepResolver;
import org.junit.Test;

public class ViaCepResolverTest {

    @Test
    public void fetchString() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);
        cep4j.fetch("15110000")
                .thenAccept(cepModel -> {
                    System.out.println("resultado: " + Json.toJson(cepModel));
                }).join();

    }

    @Test
    public void fetchString2() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);
        cep4j.fetch("15043-330")
                .thenAccept(cepModel -> {
                    System.out.println("resultado: " + Json.toJson(cepModel));
                }).join();

    }

}