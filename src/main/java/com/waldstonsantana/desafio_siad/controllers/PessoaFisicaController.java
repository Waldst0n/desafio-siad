package com.waldstonsantana.desafio_siad.controllers;

import com.waldstonsantana.desafio_siad.dtos.PessoaFisicaDto;
import com.waldstonsantana.desafio_siad.services.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pfisicas")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaFisicaDto> findbyId (@PathVariable Long id) {
        PessoaFisicaDto dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaFisicaDto>> findAll(Pageable pageable) {
        Page<PessoaFisicaDto> dto = service.findAll(pageable);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PessoaFisicaDto> insert(@RequestBody PessoaFisicaDto dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaFisicaDto> update(@PathVariable Long id, @RequestBody PessoaFisicaDto dto) {
        dto = service.update(id,dto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
