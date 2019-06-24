package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Futures;
import com.github.leofalco.cep4j.Validator;
import com.github.leofalco.cep4j.core.resolvers.base.Resolver;
import com.github.leofalco.cep4j.model.CepResponse;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Cep4j implements Cep4jInterface {

    private List<Resolver> resolvers;

    public Cep4j(Resolver... resolvers) {
        this.resolvers = Arrays.asList(resolvers);
    }

    public CompletableFuture<CepResponse> fetchAsync(String input) {
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

    public CepResponse fetch(String cep) {
        return fetchAsync(cep).join();
    }

    public void fetch(String cep, @NonNull Consumer<CepResponse> onSuccess, @NonNull Consumer<Throwable> onError) {
        fetchAsync(cep)
                .thenAccept(onSuccess)
                .exceptionally(throwable -> {
                    onError.accept(throwable);
                    return null;
                });
    }

    public void fetch(String cep, @NonNull Consumer<CepResponse> onSuccess) {
        fetchAsync(cep)
                .thenAccept(onSuccess);
    }

}
