package me.leo.cepj4.core.resolvers;

import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;
import me.leo.cepj4.model.ResponseMap;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Resolver {

    CompletableFuture<CepResponse> resolve(String cep);

    Response fetch(String cep) throws Exception;

    void handleError(ResponseMap response);

    CepResponse parseResponse(ResponseMap response);

    ServiceError parseError(ResponseMap response);

    boolean isSuccess(ResponseMap response);

    String getName();

    Map<String, Object> toMap(Response response);
}
