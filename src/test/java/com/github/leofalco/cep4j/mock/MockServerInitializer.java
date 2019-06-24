package com.github.leofalco.cep4j.mock;

import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpError;
import org.mockserver.model.HttpRequest;
import org.mockserver.server.initialize.ExpectationInitializer;

import java.util.concurrent.TimeUnit;

public class MockServerInitializer implements ExpectationInitializer {

    @Override
    public Expectation[] initializeExpectations() {
        return new Expectation[]{
                new Expectation(HttpRequest.request()
                        .withPath("api.postmon.com.br/v1/cep/")
                ).thenError(HttpError.error().withDelay(TimeUnit.SECONDS, 5)),
                new Expectation(HttpRequest.request()
                        .withPath("viacep.com.br/ws/")
                )
        };
    }
}
