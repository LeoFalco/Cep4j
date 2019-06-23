package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

public class Cep4jTest {

    @Test
    public void fetchRuaAuriflamaTest() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());

        CepResponse join = cep4j.fetch("15043330").join();

    }


    @Test
    public void fetchDistritoTest() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());

        CepResponse join = cep4j.fetch("15154000").join();

    }

}