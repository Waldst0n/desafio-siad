package com.waldstonsantana.desafio_siad.repositories;

import com.waldstonsantana.desafio_siad.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
