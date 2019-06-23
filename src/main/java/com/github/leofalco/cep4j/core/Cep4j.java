package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Futures;
import com.github.leofalco.cep4j.Validator;
import com.github.leofalco.cep4j.core.resolvers.base.Resolver;
import com.github.leofalco.cep4j.model.CepResponse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Cep4j implements Cep4jInterface {

    private List<Resolver> resolvers;


    @Override
    public CompletableFuture<CepResponse> fetch(String input) {

        return CompletableFuture
                .supplyAsync(() -> Validator.tratarInput(input))
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
