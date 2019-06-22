package com.github.leofalco.cep4j;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    @SuppressWarnings("ConstantConditions") // intentional
    public void emptyToNull() {
        String s = Strings.emptyToNull("");
        Assert.assertNull(s);

        String nulo = Strings.emptyToNull(null);
        Assert.assertNull(nulo);

        String ola = Strings.emptyToNull("Olá mundo!");
        Assert.assertEquals("Olá mundo!", ola);
    }

    @SuppressWarnings("ConstantConditions") // intentional
    @Test
    public void onlyDigits() {
        String s = Strings.onlyDigits("123.4$58.oi87687@#@987#$");
        Assert.assertEquals("12345887687987", s);

        String empty = Strings.onlyDigits("");
        Assert.assertEquals("", empty);


        String nulo = Strings.onlyDigits(null);
        Assert.assertNull(nulo);

    }

    @Test
    public void constructor() {

        try {
            Strings.class.newInstance();
            Assert.fail();
        } catch (InstantiationException | IllegalAccessException ignored) {
        }

    }
}