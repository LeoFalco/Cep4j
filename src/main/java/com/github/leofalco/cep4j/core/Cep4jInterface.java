package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.model.CepResponse;

import java.util.concurrent.CompletableFuture;

public interface Cep4jInterface {

    CompletableFuture<CepResponse> fetch(String cep);
}