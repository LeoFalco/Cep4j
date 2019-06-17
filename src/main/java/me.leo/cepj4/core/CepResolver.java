package me.leo.cepj4.core;

import me.leo.cepj4.model.Cep;

public interface CepResolver {

    public Cep cep(int cep);

    public Cep cep(String cep);
}