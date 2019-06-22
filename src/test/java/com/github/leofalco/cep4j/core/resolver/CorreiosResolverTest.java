package com.github.leofalco.cep4j.core.resolver;

import com.github.leofalco.cep4j.core.Cep4j;
import com.github.leofalco.cep4j.core.resolvers.postmon.PostmonResolver;
import com.github.leofalco.cep4j.exceptions.ManyException;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.CepResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletionException;

public class CorreiosResolverTest {

    @Test
    public void fetchGuapiacu() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        CepResponse cep = cep4j.fetch("15110000").join();

        Assert.assertEquals("Postmon", cep.getResolver());
        Assert.assertEquals("15110000", cep.getCep());
        Assert.assertEquals("São Paulo", cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("Guapiaçu", cep.getCidade());
        Assert.assertNull(cep.getBairro());
        Assert.assertNull(cep.getLogradouro());
        Assert.assertEquals("3517505", cep.getIbge());
    }

    @Test
    public void fetchRuaAuriflama() {
        Cep4j cep4j = new Cep4j(new PostmonResolver());
        CepResponse cep = cep4j.fetch("15043330").join();

        Assert.assertEquals("Postmon", cep.getResolver());
        Assert.assertEquals("15043330", cep.getCep());
        Assert.assertEquals("São Paulo", cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("São José do Rio Preto", cep.getCidade());
        Assert.assertEquals("Eldorado", cep.getBairro());
        Assert.assertEquals("Rua Auriflama", cep.getLogradouro());
        Assert.assertEquals("3549805", cep.getIbge());
    }

    @Test
    public void fetchEngenheiroBalduino() {

        Cep4j cep4j = new Cep4j(new PostmonResolver());

        CepResponse cep = cep4j.fetch("15154000").join();

        Assert.assertEquals("Postmon", cep.getResolver());
        Assert.assertEquals("15154000", cep.getCep());
        Assert.assertEquals("São Paulo", cep.getEstado());
        Assert.assertEquals("SP", cep.getUf());
        Assert.assertEquals("Monte Aprazível", cep.getCidade());
        Assert.assertEquals("Engenheiro Balduíno", cep.getBairro());
        Assert.assertNull(cep.getLogradouro());
        Assert.assertEquals("3531407", cep.getIbge());
    }


    @Test
    public void failWenCepNotExistsWithCorrectMessages() {
        try {
            Cep4j cep4j = new Cep4j(new PostmonResolver());
            cep4j.fetch("15110040").join();
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
            Assert.assertEquals("Postmon", serviceException.getServiceName());
            Assert.assertEquals("404", serviceException.getCode());
            Assert.assertEquals("Erro", serviceException.getMessage());
            Assert.assertEquals("Cep não encontrado na base do Postmon", serviceException.getDescription());
        }
    }
}
