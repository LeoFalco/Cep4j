package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.model.Cep;
import lombok.NonNull;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface Cep4jInterface {

    CompletableFuture<CepResponse> fetch(String cep);
}