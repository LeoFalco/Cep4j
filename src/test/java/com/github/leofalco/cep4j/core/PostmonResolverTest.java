package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolvers.postmon.PostmonResolver;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Test;

import java.util.concurrent.CompletionException;

public class PostmonResolverTest {

    @Test
    public void successTest() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        CepResponse cep = cep4j.fetch("15110000").join();
        System.out.println("Resultado: " + new Json().stringfy(cep));
    }

    @Test
    public void failWenCepNotExists() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        try {
            cep4j.fetch("15110040").join();
        } catch (CompletionException e) {
            System.out.println("Resultado: " + e.getCause().getMessage());
        }
    }

}
