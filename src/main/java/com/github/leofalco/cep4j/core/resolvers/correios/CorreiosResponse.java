package com.github.leofalco.cep4j.core.resolvers.correios;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.github.leofalco.cep4j.core.resolvers.CepRenponsable;
import com.github.leofalco.cep4j.model.CepResponse;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CorreiosResponse implements CepRenponsable {
    @JsonAlias("Body")
    private Body body;


    public final CepResponse toCepResponse() {
        Retorno retorno = body.getResponse().getRetorno();
        return new CepResponse("Correios",
                retorno.getCep(),
                retorno.getLogradouro(),
                retorno.getComplemento(),
                retorno.getBairro(),
                retorno.getUf(),
                retorno.getCidade(),
                retorno.getIbge());
    }
}


