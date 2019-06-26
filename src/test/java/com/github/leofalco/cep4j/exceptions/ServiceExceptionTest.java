package com.github.leofalco.cep4j.exceptions;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ServiceExceptionTest {

    @Test
    public void ofExceptionTest() {
        ServiceException serviceException = new ServiceException("test", new RuntimeException("Boom!"));

        assertThat(serviceException.getServiceName()).isEqualTo("test");
        assertThat(serviceException.getCode()).isEqualTo("RuntimeException");
        assertThat(serviceException.getMessage()).isEqualTo("service=test code=RuntimeException mensagem=Boom! description=null");
        assertThat(serviceException.getDescription()).isNull();

    }
}