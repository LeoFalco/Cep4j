package me.leo.cepj4.core;

import me.leo.cepj4.Http;
import me.leo.cepj4.Json;
import me.leo.cepj4.model.CepModel;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ViaCepResolver implements Resolver {

    private static final String BASE_URL = "https://viacep.com.br/ws/{cep}/json/";

    @Override
    public CompletableFuture<CepModel> resolve(String cep) {
        String uri = BASE_URL.replace("{cep}", cep);

        return CompletableFuture.supplyAsync(() -> {
            try {
                String response = Http.get(uri);
                return Json.fromJson(response, CepModel.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
