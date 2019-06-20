package me.leo.cepj4.core.resolvers.viacep;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.leo.cepj4.model.CepResponse;

@Getter
@Setter
@NoArgsConstructor
public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;


    public final CepResponse toCepResponse() {
        return new CepResponse("ViaCep", cep, logradouro, complemento, bairro, localidade, uf, ibge);
    }
}
