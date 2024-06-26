package com.waldstonsantana.desafio_siad.dtos;

import com.waldstonsantana.desafio_siad.models.Fisica;

import java.time.LocalDate;
import java.util.List;

public class PessoaFisicaDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;



    public PessoaFisicaDto(Long id, String name, LocalDate dtaNascimento, String cpf) {
        this.id = id;
        this.nome = name;
        this.dataNascimento = dtaNascimento;
        this.cpf = cpf;
    }

    public PessoaFisicaDto(Fisica entity){
        id = entity.getId();
        nome = entity.getNome();
        dataNascimento = entity.getDataNascimento();
        cpf = entity.getCpf();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }
}
