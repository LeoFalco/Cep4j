package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.correios.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.postmon.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.viacep.ViaCepResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

public class SpeedTest {

    @Test
    public void fetchPostMon() {

        Cep4j cep4j = new Cep4j(new PostmonResolver());

        CepResponse join = cep4j.fetch("15154000").join();

        System.out.println("Resultado: " + new Json().stringfy(join));
    }

    @Test
    public void fetchViaCep() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver());

        CepResponse join = cep4j.fetch("15154000").join();

        System.out.println("Resultado: " + new Json().stringfy(join));
    }


    @Test
    public void fetchCorreios() {

        Cep4j cep4j = new Cep4j(new CorreiosResolver());

        CepResponse join = cep4j.fetch("15154000").join();

        System.out.println("Resultado: " +  Json.stringfy(join));
    }
}
