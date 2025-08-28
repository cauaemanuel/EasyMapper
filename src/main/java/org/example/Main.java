package org.example;

import org.example.mappers.EasyMapper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        var easy = new EasyMapper();
        var pessoa = new org.example.dto.Pessoa();
        pessoa.setNome("João");
        var dto = easy.toDto(pessoa, org.example.dto.PessoaDTO.class);
        System.out.println(dto.getNome());
    }
}