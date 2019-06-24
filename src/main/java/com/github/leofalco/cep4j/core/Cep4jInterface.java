package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.model.CepResponse;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface Cep4jInterface {

    CompletableFuture<CepResponse> fetchAsync(String input);

    CepResponse fetch(String cep);

    void fetch(String cep, @NonNull Consumer<CepResponse> onSuccess);

    void fetch(String cep, @NonNull Consumer<CepResponse> onSuccess, @NonNull Consumer<Throwable> onError);

}