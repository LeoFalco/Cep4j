package com.github.leofalco.cep4j.core.resolvers.viacep;

import com.github.leofalco.cep4j.core.resolvers.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;
import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.model.CepResponse;

import java.util.Map;

public class ViaCepResolver extends ResolverBase {

    private static final String BASE_URL = "https://viacep.com.br/ws/";

    @Override
    public Response fetch(String cep) throws Exception{
        String uri = BASE_URL.concat(cep).concat("/json");
        return Http.get(uri);
    }

    @Override
    public CepResponse parseResponse(ResponseMap response) {
        return new Json().convert(response.getMap(), ViaCepResponse.class).toCepResponse();
    }

    @Override
    public ServiceException parseError(ResponseMap response) {
        return new ServiceException(response.getStatus(), String.valueOf(response.getMap().get("erro")), "", getName());
    }

    @Override
    public boolean isSuccess(ResponseMap response) {
        if (!"200".equals(response.getStatus())) {
            return false;
        }

        Object error = response.getMap().get("erro");
        return error == null || !(boolean) error;

    }

    @Override
    public Map<String, Object> toMap(Response response) {
        return new Json().toMap(response.getContent());
    }

    @Override
    public String getName() {
        return "ViaCep";
    }
}
