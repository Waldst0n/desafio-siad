package com.waldstonsantana.desafio_siad.repositories;

import com.waldstonsantana.desafio_siad.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
