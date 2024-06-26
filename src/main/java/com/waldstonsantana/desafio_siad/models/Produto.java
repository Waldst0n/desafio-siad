package com.waldstonsantana.desafio_siad.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nome;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "juridica_id")
    private Juridica juridica;

    @OneToMany(mappedBy = "produto")
    private List<Venda> vendas = new ArrayList<>();

    public Produto() {}

    public Produto(Long id, String nome, Double valor, Juridica juridica) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.juridica = juridica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Juridica getJuridica() {
        return juridica;
    }

    public void setJuridica(Juridica juridica) {
        this.juridica = juridica;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
}
