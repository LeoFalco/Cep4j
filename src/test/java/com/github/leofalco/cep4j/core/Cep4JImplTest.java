package com.github.leofalco.cep4j.core;

import com.github.leofalco.cep4j.Json;
import com.github.leofalco.cep4j.core.resolver.ResolverTest;
import com.github.leofalco.cep4j.core.resolvers.impl.CorreiosResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.PostmonResolver;
import com.github.leofalco.cep4j.core.resolvers.impl.ViaCepResolver;
import com.github.leofalco.cep4j.exceptions.ManyException;
import com.github.leofalco.cep4j.exceptions.ServiceException;
import com.github.leofalco.cep4j.model.Cep;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class Cep4JImplTest implements ResolverTest {

    private static Cep4jImpl cep4j;

    @BeforeClass
    public static void setup() throws IOException {
        cep4j = new Cep4jImpl(new ViaCepResolver(), new PostmonResolver(), new CorreiosResolver());
    }

    @AfterClass
    public static void shutdown() {
        //clientAndServer.stop();
    }

    @Test
    public void validationFail() {
        try {
            cep4j.fetch("110000");
            Assertions.failBecauseExceptionWasNotThrown(CompletionException.class);
        } catch (CompletionException e) {
            assertThat(e.getCause()).isInstanceOf(ManyException.class);

            ManyException many = (ManyException) e.getCause();
            assertThat(many.getThrowableList()).hasSize(1);

            Throwable validationExeception = many.getThrowableList().get(0);

            assertThat(validationExeception).isInstanceOf(ServiceException.class);
            ServiceException serviceException = (ServiceException) validationExeception;
            assertThat(serviceException.getServiceName()).isEqualTo("Validator");
            assertThat(serviceException.getCode()).isEqualTo("invalid_input");
            assertThat(serviceException.getMensagem()).isEqualTo("Cep inválido");
            assertThat(serviceException.getDescription()).isEqualTo("Cep deve conter 8 caracteres.");
        }

    }


    @Test
    @Override
    public void fetchGuapiacu() {
        CompletableFuture<Cep> fetch = cep4j.fetchAsync("15110-000");
        Cep cep = fetch.join();
        assertThat(cep.getCep()).isEqualTo("15110000");
        assertThat(cep.getCidade()).isEqualTo("Guapiaçu");
    }

    @Test
    @Override
    public void fetchRuaAuriflama() {
        Cep fetch = cep4j.fetch("15043-330");
    }


    @Test
    @Override
    public void fetchEngenheiroBalduino() {

    }

    @Test
    @Override
    public void failWenCepNotExistsWithCorrectMessages() {
        try {
            cep4j.fetch("15110040");
            Assert.fail();
        } catch (CompletionException e) {

            Assert.assertEquals(ManyException.class, e.getCause().getClass());

            ManyException manyException = (ManyException) e.getCause();
            List<Throwable> errorList = manyException.getThrowableList();
            Throwable throwable = errorList.get(0);
            Throwable cause = throwable.getCause();

            Assert.assertEquals(CompletionException.class, throwable.getClass());
            Assert.assertEquals(ServiceException.class, cause.getClass());

        }
    }

    @Test
    public void fetchTest() throws InterruptedException {
        cep4j.fetch("15043-330");

        Throwable throwable = catchThrowable(() -> cep4j.fetch("12345679"));

        assertThat(throwable.getCause()).isInstanceOf(ManyException.class);


        AtomicBoolean completed = new AtomicBoolean(false);
        cep4j.fetch("15110-000", cep -> {
            assertThat(cep.getCidade()).isEqualTo("Guapiaçu");
            completed.set(true);
        });

        do {
            Thread.sleep(1000);
        } while (!completed.get());

        completed.set(false);

        cep4j.fetch("99999-999", cep -> Assert.fail(),
                erro -> {
                    assertThat(erro).isInstanceOf(CompletionException.class);
                    completed.set(true);
                });

        do {
            Thread.sleep(1000);
        } while (!completed.get());

        completed.set(false);


    }
}
