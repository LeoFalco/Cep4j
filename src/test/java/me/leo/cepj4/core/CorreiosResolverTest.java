package me.leo.cepj4.core;

import me.leo.cepj4.core.resolvers.correios.CorreiosResolver;
import me.leo.cepj4.model.CepResponse;
import org.junit.Test;

import java.util.jar.JarEntry;

public class CorreiosResolverTest {

    @Test
    public void test(){
        Cep4j cep4j = new Cep4j(new CorreiosResolver());

        CepResponse join = cep4j.fetch("15110000").join();

        System.out.println("join = " + join);
    }
}
