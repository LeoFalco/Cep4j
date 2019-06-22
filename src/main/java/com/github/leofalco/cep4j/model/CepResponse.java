package com.github.leofalco.cep4j.model;


import com.github.leofalco.cep4j.Strings;
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