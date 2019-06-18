package me.leo.cepj4.core;

import me.leo.cepj4.model.CepModel;

import java.util.concurrent.CompletableFuture;

public interface Resolver {

    CompletableFuture<CepModel> resolve(String string);
}
