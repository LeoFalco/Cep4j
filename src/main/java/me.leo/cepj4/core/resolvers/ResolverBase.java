package me.leo.cepj4.core.resolvers;

import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;
import me.leo.cepj4.model.ResponseMap;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class ResolverBase implements Resolver {

    @Override
    public final CompletableFuture<CepResponse> resolve(String string) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Response response = fetch(string);
                Map<String, Object> map = toMap(response);
                ResponseMap responseMap = new ResponseMap(response.getStatus(), map);
                handleError(responseMap);
                return parseResponse(responseMap);
            } catch (ServiceError e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                throw ServiceError.ofException(getName(), e);
            }
        });
    }

    @Override
    public final void handleError(ResponseMap response) {
        if (!isSuccess(response)) {
            throw parseError(response);
        }
    }

}
