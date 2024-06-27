package com.waldstonsantana.desafio_siad.repositories;

import com.waldstonsantana.desafio_siad.models.Juridica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaRepository extends JpaRepository<Juridica, Long> {
}
