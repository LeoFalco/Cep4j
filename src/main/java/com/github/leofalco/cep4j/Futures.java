package com.github.leofalco.cep4j;

import com.github.leofalco.cep4j.exceptions.ManyException;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

public class Futures {
    private Futures() {
    }

    public static <U> CompletableFuture<U> firstCompleted(@NonNull List<CompletableFuture<U>> input) {

        final int size = input.size();
        if (size <= 0) {
            throw new IllegalArgumentException("stages must not be empty");
        }
        final AtomicInteger index = new AtomicInteger();
        final CompletableFuture<U> future = new CompletableFuture<>();
        final List<Throwable> errors = new ArrayList<>();

        for (CompletionStage<U> item : input) {
            item.whenComplete((val, exc) -> {
                if (exc == null) {
                    future.complete(val);
                } else {
                    errors.add(exc);
                    if (index.incrementAndGet() == size) {
                        // Complete with the last exception. You can aggregate all the exceptions if you wish.
                        future.completeExceptionally(new ManyException(errors));
                    }
                }
            });
        }
        return future;
    }
}
