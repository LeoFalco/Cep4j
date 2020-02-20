package com.github.leofalco.cep4j.core.resolvers.impl;

import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.core.resolvers.base.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

public class ViaCepResolver extends ResolverBase {

    private static final String BASE_URL = "https://viacep.com.br/ws/";

    @Override
    public Response fetch(String cep) throws Exception {
        String uri = BASE_URL.concat(cep).concat("/json");
        return Http.get(uri);
    }

    @Override
    public Cep parseResponse(ResponseMap response) {


        return new Cep(getName(),
                response.get("cep"),
                null,
                response.get("uf"),
                response.get("localidade"),
                response.get("bairro"),
                response.get("logradouro"),
                response.get("ibge"));
    }

    @Override
    public ServiceException parseError(ResponseMap response) {
        return new ServiceException(getName(), response.getStatus(), "Erro", "Cep não encontrado na base do ViaCep");
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
    public String getName() {
        return "ViaCep";
    }
}
