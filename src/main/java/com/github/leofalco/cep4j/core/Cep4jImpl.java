package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Futures;
import com.github.leofalco.cep4j.Validator;
import com.github.leofalco.cep4j.core.resolvers.base.Resolver;
import com.github.leofalco.cep4j.model.Cep;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Cep4jImpl implements Cep4j {

    private List<Resolver> resolvers;

    public Cep4jImpl(Resolver... resolvers) {
        this.resolvers = Arrays.asList(resolvers);
    }

    public CompletableFuture<Cep> fetchAsync(String input) {
        return CompletableFuture
                .supplyAsync(() -> Validator.tratarInput(input))
                .thenCompose(inputTratado -> {
                    List<CompletableFuture<Cep>> futures = resolvers
                            .stream()
                            .map(resolver -> resolver.resolve(inputTratado))
                            .collect(Collectors.toList());

                    return Futures.firstCompleted(futures);
                });

    }

    public Cep fetch(String cep) {
        return fetchAsync(cep).join();
    }

    public void fetch(String cep, @NonNull Consumer<Cep> onSuccess, @NonNull Consumer<Throwable> onError) {
        fetchAsync(cep)
                .thenAccept(onSuccess)
                .exceptionally(throwable -> {
                    onError.accept(throwable);
                    return null;
                });
    }

    public void fetch(String cep, @NonNull Consumer<Cep> onSuccess) {
        fetchAsync(cep)
                .thenAccept(onSuccess);
    }

}
