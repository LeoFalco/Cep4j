package com.github.leofalco.cep4j.core.resolvers;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.Strings;
import com.github.leofalco.cep4j.Xml;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.CepResponse;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public abstract class ResolverBase implements Resolver {

    @Override
    public final CompletableFuture<CepResponse> resolve(String string) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                Response response = fetch(string);
                Objects.requireNonNull(response, "Response can not be null");
                Objects.requireNonNull(response.getContent(), "Response.content can not be null");

                Map<String, Object> map = toMap(response);
                Objects.requireNonNull(map, "toMap can not return null");

                ResponseMap responseMap = new ResponseMap(response.getStatus(), map);
                Objects.requireNonNull(responseMap, "ResponseMap can not return null");
                Objects.requireNonNull(responseMap.getMap(), "ResponseMap.map can not return null");

                handleError(responseMap);
                CepResponse cepResponse = parseResponse(responseMap);
                Objects.requireNonNull(cepResponse, "CepResponse can not return null");

                return cepResponse;
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                throw ServiceException.ofException(getName(), e);
            }

        });
    }

    @Override
    public final void handleError(ResponseMap response) {
        if (!isSuccess(response)) {
            throw parseError(response);
        }
    }

    @Override
    public Map<String, Object> toMap(Response response) {
        String contentType = response.getContentType().getValue();


        if (Strings.containsIgnoreCase(contentType, "application/json")) {
            return Json.toMap(response.getContent());
        } else if (Strings.containsIgnoreCase(contentType, "text/xml")) {
            return Xml.toMap(response.getContent());
        } else {
            throw new RuntimeException("Please implement a toMap(Response) for content type " + contentType);
        }
    }
}
