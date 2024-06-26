package com.waldstonsantana.desafio_siad.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoa_juridica")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Juridica  extends Pessoa{

    private String cnpj;

    @OneToMany(mappedBy = "juridica")
    private List<Produto> produtos = new ArrayList<>();


    public Juridica() {}



    public Juridica(Long id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
