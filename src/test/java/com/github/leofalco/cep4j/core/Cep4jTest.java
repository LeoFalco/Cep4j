package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolvers.correios.CorreiosResolver;
import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.postmon.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.viacep.ViaCepResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

public class Cep4jTest {

    @Test
    public void fetch() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());

        CepResponse join = cep4j.fetch("15043330").join();

        System.out.println("Resultado: " + new Json().stringfy(join));
    }


    @Test
    public void fetch2() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());

        CepResponse join = cep4j.fetch("15154000").join();

        System.out.println("Resultado: " + new Json().stringfy(join));
    }

}