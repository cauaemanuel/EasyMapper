package org.example;

import org.example.core.mappers.GenericMapper;
import org.example.dto.PessoaDTO;
import org.example.dto.Pessoa;

public class Main {
    public static void main(String[] args) {
        var mapper = new GenericMapper();

        // DTO para Entity
        var pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setIdadeMaior(10);
        var pessoa = mapper.mapper(pessoaDTO, Pessoa.class);
        System.out.println("DTO para Entity: " + pessoa);

        // Entity para DTO
        var pessoaEntity = new Pessoa();
        pessoaEntity.setNome("Maria");
        pessoaEntity.setIdade(25);
        var pessoaDTOConvertida = mapper.mapper(pessoaEntity, PessoaDTO.class);
        System.out.println("Entity para DTO: " + pessoaDTOConvertida);
    }
}