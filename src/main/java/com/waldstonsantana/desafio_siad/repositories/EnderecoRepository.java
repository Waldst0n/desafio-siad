package com.waldstonsantana.desafio_siad.repositories;

import com.waldstonsantana.desafio_siad.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
