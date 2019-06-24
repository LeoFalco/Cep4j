package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.model.Cep;
import org.junit.Test;

public class SpeedTest {

    @Test
    public void fetchPostMon() {

        Cep4j cep4j = new Cep4j(new PostmonResolver());

        Cep join = cep4j.fetch("15154000");

    }

    @Test
    public void fetchViaCep() {

        Cep4j cep4j = new Cep4j(new ViaCepResolver());

        Cep join = cep4j.fetch("15154000");

    }


    @Test
    public void fetchCorreios() {

        Cep4j cep4j = new Cep4j(new CorreiosResolver());

        Cep join = cep4j.fetch("15154000");

    }
}
