package me.leo.cepj4.core;

import me.leo.cepj4.model.CepModel;

public interface CepResolver {

    public CepModel cep(int cep);

    public CepModel cep(String cep);
}