package com.waldstonsantana.desafio_siad.controllers;

import com.waldstonsantana.desafio_siad.dtos.PessoaFisicaDto;
import com.waldstonsantana.desafio_siad.dtos.PessoaJuridicaDto;
import com.waldstonsantana.desafio_siad.services.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "pjuridicas")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaJuridicaDto> findById(@PathVariable Long id) {
        PessoaJuridicaDto dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaJuridicaDto>> findAll(Pageable pageable) {
        Page<PessoaJuridicaDto> dto = service.findAll(pageable);

        return  ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PessoaJuridicaDto> insert(@RequestBody PessoaJuridicaDto dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaJuridicaDto> update(@PathVariable Long id, @RequestBody PessoaJuridicaDto dto) {
        dto = service.update(id,dto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
