package me.leo.cepj4.core.resolvers.viacep;

import me.leo.cepj4.Http;
import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.ResolverBase;
import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;

public class ViaCepResolver extends ResolverBase {

    private static final String BASE_URL = "https://viacep.com.br/ws/";


    @Override
    public Response fetch(String cep) throws Exception{
        String uri = BASE_URL.concat(cep).concat("/json");
        return Http.get(uri);
    }

    @Override
    public CepResponse parseResponse(Response response) {
        return Json.convert(response.getMap(), ViaCepResponse.class).toCepResponse();
    }

    @Override
    public ServiceError parseError(Response response) {
        return new ServiceError(response.getStatus(), String.valueOf(response.getMap().get("erro")), "", getName());
    }

    @Override
    public boolean isSuccess(Response response) {
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
