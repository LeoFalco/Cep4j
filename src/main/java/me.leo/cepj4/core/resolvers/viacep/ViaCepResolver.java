package me.leo.cepj4.core.resolvers.viacep;

import me.leo.cepj4.Http;
import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.ResolverBase;
import me.leo.cepj4.exceptions.ServiceError;
import me.leo.cepj4.model.CepResponse;
import me.leo.cepj4.model.Response;
import me.leo.cepj4.model.ResponseMap;

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
    public ServiceError parseError(ResponseMap response) {
        return new ServiceError(response.getStatus(), String.valueOf(response.getMap().get("erro")), "", getName());
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
