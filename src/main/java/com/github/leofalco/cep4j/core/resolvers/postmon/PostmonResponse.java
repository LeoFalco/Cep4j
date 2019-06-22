package com.github.leofalco.cep4j.core.resolvers.postmon;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.leofalco.cep4j.core.resolvers.CepRenponsable;
import com.github.leofalco.cep4j.model.CepResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostmonResponse implements CepRenponsable {

    public String complemento;
    public String bairro;
    public String cidade;
    public String logradouro;
    @JsonAlias("estado_info")
    public EstadoInfo estadoInfo;
    public String cep;
    @JsonAlias("cidade_info")
    public CidadeInfo cidadeInfo;
    public String estado;

    @Getter
    @Setter
    public static class CidadeInfo {
        public String codigoIbge;
    }

    @Getter
    @Setter
    public static class EstadoInfo {
        public String codigoIbge;
        public String nome;
    }


    @Override
    public CepResponse toCepResponse() {
        return new CepResponse("Postmon", cep, logradouro, complemento, bairro, cidade, estadoInfo.nome, cidadeInfo.codigoIbge);
    }
}



