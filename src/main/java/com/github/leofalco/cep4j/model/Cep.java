package com.github.leofalco.cep4j.model;


import com.github.leofalco.cep4j.Strings;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class Cep {

    private final String resolver;
    private final String cep;
    private final String estado;
    private final String uf;
    private final String cidade;
    private final String bairro;
    private final String logradouro;
    private final String ibge;

    public Cep(@NonNull String resolver,
               @NonNull String cep,
               String estado,
               @NonNull String uf,
               @NonNull String cidade,
               String bairro,
               String logradouro,
               String ibge) {
        this.resolver = resolver;
        this.cep = Strings.onlyDigits(cep);
        this.estado = Strings.emptyToNull(estado);
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = Strings.emptyToNull(bairro);
        this.logradouro = Strings.emptyToNull(logradouro);
        this.ibge = Strings.emptyToNull(ibge);
    }
}