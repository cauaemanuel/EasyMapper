package org.example.dto;

import org.example.annotations.MapField;

public class PessoaDTO {

    private String nome;

    @MapField(nameField = "idade")
    private Integer idadeMaior;

    public Integer getIdadeMaior() {
        return idadeMaior;
    }

    public void setIdadeMaior(Integer idadeMaior) {
        this.idadeMaior = idadeMaior;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "nome='" + nome + '\'' +
                ", idadeMaior=" + idadeMaior +
                '}';
    }
}

