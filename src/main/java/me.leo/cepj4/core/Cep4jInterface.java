package me.leo.cepj4.core;

import me.leo.cepj4.model.CepResponse;

import java.util.concurrent.CompletableFuture;

public interface Cep4jInterface {

    CompletableFuture<CepResponse> fetch(String cep);
}