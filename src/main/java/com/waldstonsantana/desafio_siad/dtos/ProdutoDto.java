package com.waldstonsantana.desafio_siad.dtos;

import com.waldstonsantana.desafio_siad.models.Produto;

public class ProdutoDto {

    private Long id;
    private String nome;
    private Double valor;


    private PessoaJuridicaDto pessoaJuridicaDto;





    public ProdutoDto(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;

    }

    public ProdutoDto(Produto entity) {
        id = entity.getId();
        nome = entity.getNome();
        valor= entity.getValor();
        pessoaJuridicaDto = new PessoaJuridicaDto(entity.getJuridica());




    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public PessoaJuridicaDto getPessoaJuridicaDto() {
        return pessoaJuridicaDto;
    }


}
