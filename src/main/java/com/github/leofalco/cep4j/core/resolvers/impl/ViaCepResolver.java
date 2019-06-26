package com.github.leofalco.cep4j.core.resolvers.impl;

import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.core.resolvers.base.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

import java.util.Map;

public class ViaCepResolver extends ResolverBase {

    private static final String BASE_URL = "https://viacep.com.br/ws/";

    @Override
    public Response fetch(String cep) throws Exception{
        String uri = BASE_URL.concat(cep).concat("/json");
        return Http.get(uri);
    }

    @Override
    public Cep parseResponse(ResponseMap response) {

        Map map = response.getMap();
        String uf = (String) response.getMap().get("uf");
        String cep = (String) map.get("cep");
        String cidade = (String) map.get("localidade");
        String bairro = (String) map.get("bairro");
        String logradouro = (String) map.get("logradouro");
        String ibge = (String) map.get("ibge");

        return new Cep(getName(), cep, null, uf, cidade, bairro, logradouro, ibge);
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