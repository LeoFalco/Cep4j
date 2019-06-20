package me.leo.cepj4.core.resolvers;

import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;

import java.util.concurrent.CompletableFuture;

public interface Resolver {

    CompletableFuture<CepResponse> resolve(String cep);

    Response fetch(String cep) throws Exception;

    void handleError(Response response);

    CepResponse parseResponse(Response response);

    ServiceError parseError(Response response);

    boolean isSuccess(Response response);

    String getName();
}
