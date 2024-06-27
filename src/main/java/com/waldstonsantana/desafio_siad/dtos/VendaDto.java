package com.waldstonsantana.desafio_siad.dtos;

import com.waldstonsantana.desafio_siad.models.Venda;

public class VendaDto {
    private Long id;
    private Double total;
    private  Integer quantidade;

    private ProdutoDto produtoDto;

    private  PessoaFisicaDto pessoaFisicaDto;

    public VendaDto(Long id, Double total, Integer quantidade, ProdutoDto produtoDto, PessoaFisicaDto pessoaFisicaDto) {
        this.id = id;
        this.total = total;
        this.quantidade = quantidade;
        this.produtoDto = produtoDto;
        this.pessoaFisicaDto = pessoaFisicaDto;
    }

    public VendaDto(Venda entity) {
        this.id = entity.getId();
        this.total = entity.getTotal();
        this.quantidade = entity.getQuantidade();
        this.produtoDto = new ProdutoDto(entity.getProduto());
        this.pessoaFisicaDto = new PessoaFisicaDto(entity.getFisica());
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public ProdutoDto getProdutoDto() {
        return produtoDto;
    }

    public PessoaFisicaDto getPessoaFisicaDto() {
        return pessoaFisicaDto;
    }
}
