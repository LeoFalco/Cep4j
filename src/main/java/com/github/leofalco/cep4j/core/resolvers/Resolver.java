package com.github.leofalco.cep4j.core.resolvers;

import com.github.leofalco.cep4j.exceptions.ServiceError;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;
import com.github.leofalco.cep4j.model.CepResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Resolver {

    CompletableFuture<CepResponse> resolve(String cep);

    Response fetch(String cep) throws Exception;

    CepResponse parseResponse(ResponseMap response);

    ServiceError parseError(ResponseMap response);

    boolean isSuccess(ResponseMap response);

    void handleError(ResponseMap response);

    String getName();

    Map<String, Object> toMap(Response response);
}
