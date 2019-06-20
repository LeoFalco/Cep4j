package me.leo.cepj4.core.resolvers;

import me.leo.cepj4.Http;
import me.leo.cepj4.Json;
import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;

public class PostmonResolver extends ResolverBase {

    private static final String BASE_URL = "http://api.postmon.com.br/v1/cep/";

    @Override
    public Response fetch(String cep) throws Exception {
        return Http.get(BASE_URL + cep);
    }

    @Override
    public CepResponse parseResponse(Response response) {
        return Json.convert(response.getMap(), PostmonResponse.class).toCepResponse();
    }

    @Override
    public ServiceError parseError(Response response) {
        return new ServiceError(response.getStatus(), response.getStatus(),response.getStatus(), getName());
    }

    @Override
    public boolean isSuccess(Response response) {
        return "200".equals(response.getStatus());
    }


    @Override
    public String getName() {
        return "Postmon";
    }
}
