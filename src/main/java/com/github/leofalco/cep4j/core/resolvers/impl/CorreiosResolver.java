package com.github.leofalco.cep4j.core.resolvers.impl;

import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.base.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.model.ResponseMap;

import java.util.HashMap;
import java.util.Map;

public class CorreiosResolver extends ResolverBase {

    private static final String BASE_URL = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente";

    @Override
    public Response fetch(String cep) throws Exception {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/xml;charset=UTF-8");
        headers.put("cache-control", "no-cache");

        return Http.post(BASE_URL, requestBody(cep), headers);
    }

    @Override
    public Cep parseResponse(ResponseMap response) {
        Map<String, String> retorno = response.get("Body.consultaCEPResponse.return");
        return new Cep(getName(),
                retorno.get("cep"),
                null,
                retorno.get("uf"),
                retorno.get("cidade"),
                retorno.get("bairro"),
                retorno.get("end"),
                null);
    }

    @Override
    public ServiceException parseError(ResponseMap response) {
        return new ServiceException(getName(), response.getStatus(), "Erro", "Cep não encontrado na base dos Correios");
    }

    @Override
    public boolean isSuccess(ResponseMap response) {
        return response.get("Body.consultaCEPResponse") != null;
    }

    @Override
    public String getName() {
        return "Correios";
    }

    private static String requestBody(String cep) {
        return "<?xml version=\"1.0\"?> \n" + "<soapenv:Envelope \n"
                + "        xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" \n"
                + "        xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"> \n"
                + "    <soapenv:Header/> \n" + "    <soapenv:Body> \n" + "        <cli:consultaCEP> \n"
                + "            <cep>" + cep + "</cep> \n" + "        </cli:consultaCEP> \n" + "    </soapenv:Body> \n"
                + "</soapenv:Envelope>".replaceAll("\\s", "");
    }
}
