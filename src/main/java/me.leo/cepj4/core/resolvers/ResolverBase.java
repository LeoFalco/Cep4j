package me.leo.cepj4.core.resolvers;

import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;

import java.util.concurrent.CompletableFuture;

public abstract class ResolverBase implements Resolver {

    @Override
    public final CompletableFuture<CepResponse> resolve(String string) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Response response = fetch(string);
                handleError(response);
                return parseResponse(response);
            } catch (ServiceError e) {
                throw e;
            } catch (Exception e) {
                throw ServiceError.ofException(getName(), e);
            }
        });
    }

    @Override
    public final void handleError(Response response) throws ServiceError {
        if (!isSuccess(response)) {
            throw parseError(response);
        }
    }

}
