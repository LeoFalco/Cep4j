package me.leo.cepj4.core.resolvers.postmon;

import me.leo.cepj4.Http;
import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.ResolverBase;
import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;
import me.leo.cepj4.model.ResponseMap;

import java.util.Map;

public class PostmonResolver extends ResolverBase {

    private static final String BASE_URL = "http://api.postmon.com.br/v1/cep/";

    @Override
    public Response fetch(String cep) throws Exception {
        return Http.get(BASE_URL + cep);
    }

    @Override
    public CepResponse parseResponse(ResponseMap response) {
        return new Json().convert(response.getMap(), PostmonResponse.class).toCepResponse();
    }

    @Override
    public ServiceError parseError(ResponseMap response) {
        return new ServiceError(response.getStatus(), response.getStatus(),response.getStatus(), getName());
    }

    @Override
    public boolean isSuccess(ResponseMap response) {
        return "200".equals(response.getStatus());
    }

    @Override
    public Map<String, Object> toMap(Response response) {
        return new Json().toMap(response.getContent());
    }

    @Override
    public String getName() {
        return "Postmon";
    }
}
