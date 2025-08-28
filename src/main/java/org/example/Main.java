package org.example;

import org.example.core.ignores.IgnoreParam;
import org.example.core.mappers.EasyMapper;
import org.example.dto.Pessoa;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        var easy = new EasyMapper();
        var pessoaDTO = new org.example.dto.PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setIdadeMaior(10);

        var ignores = new IgnoreParam("nome");


        var dto = easy.toEntity(pessoaDTO, Pessoa.class, List.of(ignores));
        System.out.println(dto.toString());
    }
}