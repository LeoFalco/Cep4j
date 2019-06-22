package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolvers.correios.CorreiosResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

public class CorreiosResolverTest {

    @Test
    public void testFetchGuapiacu(){
        Cep4j cep4j = new Cep4j(new CorreiosResolver());

        CepResponse join = cep4j.fetch("15110000").join();

        System.out.println("join = " + join);
    }
}
