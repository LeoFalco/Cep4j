package me.leo.cepj4.core;

import me.leo.cepj4.Json;
import me.leo.cepj4.core.resolvers.correios.CorreiosResolver;
import me.leo.cepj4.core.resolvers.postmon.PostmonResolver;
import me.leo.cepj4.core.resolvers.viacep.ViaCepResolver;
import me.leo.cepj4.model.CepResponse;
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

        System.out.println("Resultado: " + new Json().stringfy(join));
    }
}
