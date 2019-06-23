package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

public class SpeedTest {

    @Test
    public void fetchPostMon() {

        Cep4j cep4j = new Cep4j(new PostmonResolver());

        CepResponse join = cep4j.fetch("15154000").join();

    }

    @Test
    public void fetchViaCep() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver());

        CepResponse join = cep4j.fetch("15154000").join();

    }


    @Test
    public void fetchCorreios() {

        Cep4j cep4j = new Cep4j(new CorreiosResolver());

        CepResponse join = cep4j.fetch("15154000").join();

    }
}
