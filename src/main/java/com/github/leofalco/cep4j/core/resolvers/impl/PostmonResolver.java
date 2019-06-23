package com.github.leofalco.cep4j.core.resolvers.impl;

import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.core.resolvers.base.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.CepResponse;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

import java.util.Map;

public class PostmonResolver extends ResolverBase {

    private static final String BASE_URL = "http://api.postmon.com.br/v1/cep/";

    @Override
    public Response fetch(String cep) throws Exception {
        return Http.get(BASE_URL + cep);
    }

    @Override
    public CepResponse parseResponse(ResponseMap response) {
        Map<String, Object> map = response.getMap();

        String cep = (String) map.get("cep");
        String estado = (String) ((Map) map.get("estado_info")).get("nome");
        String uf = (String) map.get("estado");
        String cidade = (String) map.get("cidade");
        String bairro = (String) map.get("bairro");
        String logradouro = (String) map.get("logradouro");
        String codigoIbge = (String) ((Map) map.get("cidade_info")).get("codigo_ibge");

        return new CepResponse(getName(), cep, estado, uf, cidade, bairro, logradouro, codigoIbge);

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
