package me.leo.cepj4.core;

import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.postmon.PostmonResolver;
import me.leo.cepj4.core.resolvers.viacep.ViaCepResolver;
import me.leo.cepj4.model.CepResponse;
import org.junit.Test;

public class Cep4jTest {

    @Test
    public void fetch() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver());

        CepResponse join = cep4j.fetch("15043330").join();

        System.out.println("Resultado: " + Json.toJson(join));
    }
}