package me.leo.cepj4.core;

import me.leo.cepj4.model.CepModel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Cep4j implements Cep4jInterface {

    private List<Resolver> resolvers;


    @Override
    public CompletableFuture<CepModel> fetch(int cep) {
        return fetch(Integer.toString(cep));
    }

    @Override
    public CompletableFuture<CepModel> fetch(String input) {
        String inputTratado = Util.tratarInput(input);

        CompletableFuture[] futures = resolvers.stream()
                .map(resolver -> resolver.resolve(inputTratado))
                .toArray(CompletableFuture[]::new);


        return CompletableFuture.anyOf(futures)
                .thenApply(value -> ((CepModel) value));

    }


    public Cep4j(Resolver... resolvers) {
        this.resolvers = Arrays.asList(resolvers);
    }

}
