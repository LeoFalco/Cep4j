package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.model.Cep;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpeedTest {

    @Test
    public void fetchPostMon() {

        Cep4jImpl cep4j = new Cep4jImpl(new PostmonResolver());

        Cep join = cep4j.fetch("15154000");
        assertThat(join.getCep()).isEqualTo("15154000");

    }

    @Test
    public void fetchViaCep() {

        Cep4jImpl cep4j = new Cep4jImpl(new ViaCepResolver());

        Cep join = cep4j.fetch("15154000");
        assertThat(join.getCep()).isEqualTo("15154000");

    }


    @Test
    public void fetchCorreios() {

        Cep4jImpl cep4j = new Cep4jImpl(new CorreiosResolver());

        Cep join = cep4j.fetch("15110000");
        assertThat(join.getCep()).isEqualTo("15110000");

    }
}
