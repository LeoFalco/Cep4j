package me.leo.cepj4.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CepResponse {

    private String resolver;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;

    public CepResponse(String resolver,
                       String cep,
                       String logradouro,
                       String complemento,
                       String bairro,
                       String localidade,
                       String uf,
                       String ibge) {
        this.resolver = resolver;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
    }
}