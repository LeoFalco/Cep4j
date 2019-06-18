package me.leo.cepj4.core;

import me.leo.cepj4.model.CepModel;

import java.util.concurrent.CompletableFuture;

public interface Cep4jInterface {

    CompletableFuture<CepModel> fetch(int cep);

    CompletableFuture<CepModel> fetch(String cep);
}