package me.leo.cepj4.core;

import me.leo.cepj4.Futures;
import me.leo.cepj4.Util;
import me.leo.cepj4.core.resolvers.Resolver;
import me.leo.cepj4.model.CepResponse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Cep4j implements Cep4jInterface {

    private List<Resolver> resolvers;


    @Override
    public CompletableFuture<CepResponse> fetch(String input) {

        return CompletableFuture
                .supplyAsync(() -> Util.tratarInput(input))
                .thenCompose(inputTratado -> {
                    List<CompletableFuture<CepResponse>> futures = resolvers
                            .stream()
                            .map(resolver -> resolver.resolve(inputTratado))
                            .collect(Collectors.toList());

                    return Futures.firstCompleted(futures);
                });

    }


    public Cep4j(Resolver... resolvers) {
        this.resolvers = Arrays.asList(resolvers);
    }

}
