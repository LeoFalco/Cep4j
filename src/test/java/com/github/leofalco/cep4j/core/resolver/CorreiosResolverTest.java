package com.github.leofalco.cep4j.core.resolver;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.Cep4jImpl;
import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.exceptions.ManyException;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import org.assertj.core.api.Condition;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThat;

public class CorreiosResolverTest {

    @Test
    public void fetchGuapiacu() {
        Cep4jImpl cep4j = new Cep4jImpl(new CorreiosResolver());
        Cep cep = cep4j.fetch("15110000");

        Assert.assertEquals("Correios", cep.getResolver());
        Assert.assertEquals("15110000", cep.getCep());
        Assert.assertNull(cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("Guapiaçu", cep.getCidade());
        Assert.assertNull(cep.getBairro());
        Assert.assertNull(cep.getLogradouro());
        Assert.assertNull(cep.getIbge());
    }

    @Test
    public void fetchRuaAuriflama() {
        Cep4jImpl cep4j = new Cep4jImpl(new CorreiosResolver());
        Cep cep = cep4j.fetch("15043330");

        Assert.assertEquals("Correios", cep.getResolver());
        Assert.assertEquals("15043330", cep.getCep());
        Assert.assertNull(cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("São José do Rio Preto", cep.getCidade());
        Assert.assertEquals("Eldorado", cep.getBairro());
        Assert.assertEquals("Rua Auriflama", cep.getLogradouro());
        Assert.assertNull(cep.getIbge());
    }

    @Test
    public void fetchEngenheiroBalduino() {

        Cep4jImpl cep4j = new Cep4jImpl(new CorreiosResolver());

        Cep cep = cep4j.fetch("15154000");

        Assert.assertEquals("Correios", cep.getResolver());
        Assert.assertEquals("15154000", cep.getCep());
        Assert.assertNull(cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("Engenheiro Balduíno (Monte Aprazível)", cep.getCidade());
        Assert.assertNull(cep.getBairro());
        Assert.assertNull(cep.getLogradouro());
        Assert.assertNull(cep.getIbge());
    }


    @Test
    public void failWenCepNotExistsWithCorrectMessages() {
        try {
            Cep4jImpl cep4j = new Cep4jImpl(new CorreiosResolver());
            cep4j.fetch("15110040");
            Assert.fail();
        } catch (CompletionException e) {

            Assert.assertEquals(ManyException.class, e.getCause().getClass());

            ManyException manyException = (ManyException) e.getCause();
            List<Throwable> errorList = manyException.getThrowableList();
            Throwable throwable = errorList.get(0);
            Throwable cause = throwable.getCause();

            Assert.assertEquals(1, errorList.size());
            Assert.assertEquals(CompletionException.class, throwable.getClass());
            Assert.assertEquals(ServiceException.class, cause.getClass());


            ServiceException serviceException = (ServiceException) cause;

            assertThat(serviceException.getServiceName()).isEqualTo("Correios");
            Condition<String> inCodes = new Condition<>(s -> "200".equals(s) || "500".equals(s), "Allowed codes");
            assertThat(serviceException.getCode()).is(inCodes);
            assertThat(serviceException.getMensagem()).isEqualTo("Erro");
            assertThat(serviceException.getDescription()).isEqualTo("Cep não encontrado na base dos Correios");
        }
    }
}
