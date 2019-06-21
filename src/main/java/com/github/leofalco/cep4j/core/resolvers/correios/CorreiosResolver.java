package com.github.leofalco.cep4j.core.resolvers.correios;

import com.github.leofalco.cep4j.core.resolvers.ResolverBase;
import com.github.leofalco.cep4j.exceptions.ServiceError;
import com.github.leofalco.cep4j.model.Response;
import com.github.leofalco.cep4j.Http;
import com.github.leofalco.cep4j.Xml;
import com.github.leofalco.cep4j.model.CepResponse;
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
    public CepResponse parseResponse(ResponseMap response) {
        return new Xml().convert(response.getMap(), CorreiosResponse.class).toCepResponse();
    }

    @Override
    public ServiceError parseError(ResponseMap response) {
        return null;
    }

    @Override
    public boolean isSuccess(ResponseMap response) {
        return "200".equals(response.getStatus());
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> toMap(Response response) {
        return new Xml().toMap(response.getContent());
    }

    private static String requestBody(String cep) {
        return "<?xml version=\"1.0\"?> \n" +
                "<soapenv:Envelope \n" +
                "        xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" \n" +
                "        xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"> \n" +
                "    <soapenv:Header/> \n" +
                "    <soapenv:Body> \n" +
                "        <cli:consultaCEP> \n" +
                "            <cep>" + cep + "</cep> \n" +
                "        </cli:consultaCEP> \n" +
                "    </soapenv:Body> \n" +
                "</soapenv:Envelope>".replaceAll("\\s", "");
    }
}
