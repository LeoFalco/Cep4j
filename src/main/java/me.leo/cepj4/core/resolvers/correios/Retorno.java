package me.leo.cepj4.core.resolvers.correios;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Retorno {
    private String cep;
    private String logradouro;
    @JsonAlias("complemento2")
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String ibge;
}
