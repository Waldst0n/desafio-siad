package com.waldstonsantana.desafio_siad.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Double total;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "fisica_id")
    private  Fisica fisica;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public  Venda() {}

    public Venda(Long id, Double total, Integer quantidade, Fisica fisica, Produto produto) {
        this.id = id;
        this.total = total;
        this.quantidade = quantidade;
        this.fisica = fisica;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fisica getFisica() {
        return fisica;
    }

    public void setFisica(Fisica fisica) {
        this.fisica = fisica;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
