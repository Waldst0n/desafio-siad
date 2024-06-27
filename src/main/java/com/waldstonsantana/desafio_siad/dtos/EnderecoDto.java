package com.waldstonsantana.desafio_siad.dtos;

import com.waldstonsantana.desafio_siad.models.Endereco;

public class EnderecoDto {

    private Long id;
    private String cep;
    private String cidade;
    private String bairro;
    private  String rua;
    private String numero;

    private PessoaFisicaDto pessoaFisicaDto;

    public EnderecoDto(Long id,String cep, String cidade, String bairro, String rua, String numero, PessoaFisicaDto pessoaFisicaDto) {
        this.id = id;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.pessoaFisicaDto = pessoaFisicaDto;
    }

    public EnderecoDto(Endereco entity) {
        id = entity.getId();
        cep = entity.getCep();
        cidade = entity.getCidade();
        bairro = entity.getBairro();
        rua = entity.getRua();
        numero = entity.getNumero();
        pessoaFisicaDto = new PessoaFisicaDto(entity.getFisica());
    }


    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public PessoaFisicaDto getPessoaFisicaDto() {
        return pessoaFisicaDto;
    }

}
