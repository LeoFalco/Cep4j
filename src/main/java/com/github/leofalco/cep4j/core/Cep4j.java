package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.model.Cep;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface Cep4j {

    CompletableFuture<Cep> fetchAsync(@NonNull String cep);

    Cep fetch(@NonNull String cep);

    void fetch(@NonNull String cep, @NonNull Consumer<Cep> onSuccess, @NonNull Consumer<Throwable> onError);

    void fetch(@NonNull String cep, @NonNull Consumer<Cep> onSuccess);
}
