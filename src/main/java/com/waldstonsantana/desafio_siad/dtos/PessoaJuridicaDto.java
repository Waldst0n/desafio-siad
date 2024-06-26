package com.waldstonsantana.desafio_siad.dtos;

import com.waldstonsantana.desafio_siad.models.Juridica;

public class PessoaJuridicaDto {

    private Long id;
    private String nome;
    private  String cnpj;

    public PessoaJuridicaDto(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public  PessoaJuridicaDto(Juridica entity) {
        id = entity.getId();
        nome = entity.getNome();
        cnpj = entity.getCnpj();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
}
