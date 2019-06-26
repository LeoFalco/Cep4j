package com.github.leofalco.cep4j.model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class CepTest {


    @Test
    public void constructorTestNonNull() {

        Throwable throwableResolver = catchThrowable(() -> new Cep(null, "a", "a", "a", "a", "a", "a", "a"));
        assertThat(throwableResolver).isInstanceOf(NullPointerException.class);

        Throwable throwableCep = catchThrowable(() -> new Cep("a", null, "a", "a", "a", "a", "a", "a"));
        assertThat(throwableCep).isInstanceOf(NullPointerException.class);

        Throwable throwableUf = catchThrowable(() -> new Cep("a", "a", "a", null, "a", "a", "a", "a"));
        assertThat(throwableUf).isInstanceOf(NullPointerException.class);

        Throwable throwableCidade = catchThrowable(() -> new Cep("a", "a", "a", "a", null, "a", "a", "a"));
        assertThat(throwableCidade).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void constructorTestNonEmpty() {
        Throwable throwableResolver = catchThrowable(() -> new Cep("", "a", "a", "a", "a", "a", "a", "a"));
        assertThat(throwableResolver).isInstanceOf(InvalidParameterException.class);

        Throwable throwableCep = catchThrowable(() -> new Cep("a", "", "a", "a", "a", "a", "a", "a"));
        assertThat(throwableCep).isInstanceOf(InvalidParameterException.class);

        Throwable throwableUf = catchThrowable(() -> new Cep("a", "a", "a", "", "a", "a", "a", "a"));
        assertThat(throwableUf).isInstanceOf(InvalidParameterException.class);

        Throwable throwableCidade = catchThrowable(() -> new Cep("a", "a", "a", "a", "", "a", "a", "a"));
        assertThat(throwableCidade).isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void constructorTestEmptyToNull() {
        Cep cep = new Cep("a", "15110000", "", "a", "a", "", "", "");

        assertThat(cep.getEstado()).isNull();
        assertThat(cep.getBairro()).isNull();
        assertThat(cep.getLogradouro()).isNull();
        assertThat(cep.getIbge()).isNull();
    }

    @Test
    public void toStringTest() {
        Cep cep = new Cep("a", "15110000", "", "a", "a", "", "", "");
        String s = cep.toString();
    }



}