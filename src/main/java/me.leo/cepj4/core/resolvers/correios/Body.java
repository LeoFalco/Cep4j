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
public class Body {
    @JsonAlias("consultaCEPResponse")
    private ConsultaCEPResponse response;
}
