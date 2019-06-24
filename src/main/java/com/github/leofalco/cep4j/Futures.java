package com.github.leofalco.cep4j;

import com.github.leofalco.cep4j.exceptions.ManyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class Futures {
    private Futures() {
    }

    public static <U> CompletableFuture<U> firstCompleted(List<CompletableFuture<U>> input) {

        List<CompletableFuture<U>> completableFutures = Collections.synchronizedList(input);

        final int count = completableFutures.size();
        if (count <= 0) {
            throw new IllegalArgumentException("stages must not be empty");
        }
        final AtomicInteger settled = new AtomicInteger();
        final CompletableFuture<U> future = new CompletableFuture<>();
        final List<Throwable> errors = new ArrayList<>();
        BiConsumer<U, Throwable> consumer = (val, exc) -> {
            if (exc == null) {
                future.complete(val);
                completableFutures.remove(settled.incrementAndGet());
                for (CompletableFuture<U> stage : completableFutures) {
                    stage.cancel(true);
                }
            } else {
                errors.add(exc);
                if (settled.incrementAndGet() == count) {
                    // Complete with the last exception. You can aggregate all the exceptions if you wish.
                    future.completeExceptionally(new ManyException(errors));
                }

            }
        };
        for (CompletionStage<U> item : completableFutures) {
            item.whenComplete(consumer);
        }
        return future;
    }
}
