package me.leo.cepj4.core.resolvers;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import me.leo.cepj4.model.CepResponse;

@Getter
@Setter
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
    public class CidadeInfo {
        public String codigoIbge;
    }

    @Getter
    @Setter
    public class EstadoInfo {
        public String codigoIbge;
        public String nome;
    }


    @Override
    public CepResponse toCepResponse() {
        return new CepResponse("Postmon", cep, logradouro, complemento, bairro, cidade, estadoInfo.nome, cidadeInfo.codigoIbge);
    }
}



