package com.waldstonsantana.desafio_siad.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoa_fisica")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Fisica  extends Pessoa{

    private String cpf;

    @OneToMany(mappedBy = "fisica")
    private List<Venda> vendas = new ArrayList<>();

    @OneToMany(mappedBy = "fisica")
    private List<Endereco> enderecos = new ArrayList<>();

    public Fisica() {}

    public Fisica(Long id, String nome, LocalDate dataNascimento, String cpf) {
        super(id, nome, dataNascimento);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }
}
