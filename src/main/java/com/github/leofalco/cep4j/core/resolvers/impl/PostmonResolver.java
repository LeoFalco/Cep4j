package com.github.leofalco.cep4j.core.resolvers.impl;

import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.core.resolvers.base.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

public class PostmonResolver extends ResolverBase {

    private static final String BASE_URL = "http://api.postmon.com.br/v1/cep/";

    @Override
    public Response fetch(String cep) throws Exception {
        return Http.get(BASE_URL + cep);
    }

    @Override
    public Cep parseResponse(ResponseMap response) {
        return new Cep(getName(),
                response.get("cep"),
                response.get("estado_info.nome"),
                response.get("estado"),
                response.get("cidade"),
                response.get("bairro"),
                response.get("logradouro"),
                response.get("cidade_info.codigo_ibge"));

    }

    @Override
    public ServiceException parseError(ResponseMap response) {
        return new ServiceException(getName(), response.getStatus(), "Erro", "Cep não encontrado na base do Postmon");
    }

    @Override
    public boolean isSuccess(ResponseMap response) {
        return "200".equals(response.getStatus());
    }

    @Override
    public String getName() {
        return "Postmon";
    }
}
