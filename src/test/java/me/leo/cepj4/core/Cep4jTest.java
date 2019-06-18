package me.leo.cepj4.core;

import me.leo.cepj4.Json;
import org.junit.Test;

public class Cep4jTest {

    @Test
    public void fetchString() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);
        cep4j.fetch("15110000")
                .thenAccept(cepModel -> {
                    System.out.println(Json.toJson(cepModel));
                }).join();

    }

    @Test
    public void fetchInt() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);

        cep4j.fetch(15110000)
                .thenAccept(cepModel -> {
                    System.out.println(Json.toJson(cepModel));
                }).join();

    }


    @Test
    public void fetch() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);

        cep4j.fetch(1455110000)
                .thenAccept(cepModel -> {
                    System.out.println(Json.toJson(cepModel));
                }).join();

    }


    @Test
    public void fetch2() {
        ViaCepResolver viaCepResolver = new ViaCepResolver();
        Cep4j cep4j = new Cep4j(viaCepResolver);

        cep4j.fetch(10000)
                .thenAccept(cepModel -> {
                    System.out.println(Json.toJson(cepModel));
                }).join();

    }
}