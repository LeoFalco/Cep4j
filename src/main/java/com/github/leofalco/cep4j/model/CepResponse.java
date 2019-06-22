package com.github.leofalco.cep4j.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CepResponse {

    private String resolver;
    private String cep;
    private String estado;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String ibge;

    public CepResponse(@NonNull String resolver,
                       @NonNull String cep,
                       @NonNull String estado,
                       String uf,
                       @NonNull String cidade,
                       String bairro,
                       String logradouro,
                       String ibge) {
        this.resolver = resolver;
        this.cep = cep.replaceAll("\\D", "");
        this.estado = estado;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.ibge = ibge;
    }
}