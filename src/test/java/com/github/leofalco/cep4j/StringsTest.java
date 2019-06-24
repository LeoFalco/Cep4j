package com.github.leofalco.cep4j;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void contains() {
        assertThat(Strings.contains(null, null)).isTrue();
        assertThat(Strings.contains("", "")).isTrue();
        assertThat(Strings.contains("abc", "")).isTrue();
        assertThat(Strings.contains("", "abc")).isFalse();
        assertThat(Strings.contains("efabcd", "abc")).isTrue();
        assertThat(Strings.contains("efaBcd.9#", "abc")).isFalse();
        assertThat(Strings.contains("efabCd", "AbC")).isFalse();
        assertThat(Strings.contains("efAbcd", "ABC")).isFalse();
        assertThat(Strings.contains("efabc##d", "abc##")).isTrue();
        assertThat(Strings.contains("efab.cd", "ab.C")).isFalse();
    }


    @Test
    public void containsIgnoreCase() {
        assertThat(Strings.containsIgnoreCase(null, null)).isTrue();
        assertThat(Strings.containsIgnoreCase("", "")).isTrue();
        assertThat(Strings.containsIgnoreCase("abc", "")).isTrue();
        assertThat(Strings.containsIgnoreCase("", "abc")).isFalse();
        assertThat(Strings.containsIgnoreCase("efaBcd.9#", "abc")).isTrue();
        assertThat(Strings.containsIgnoreCase("efabCd", "AbC")).isTrue();
        assertThat(Strings.containsIgnoreCase("efAbcd", "ABC")).isTrue();
        assertThat(Strings.containsIgnoreCase("efabc##d", "abc##")).isTrue();
        assertThat(Strings.containsIgnoreCase("efab.cd", "ab.C")).isTrue();
    }

}