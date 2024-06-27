package com.waldstonsantana.desafio_siad.controllers;

import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.repositories.PessoaJuridicaRepository;
import com.waldstonsantana.desafio_siad.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> findbyId (@PathVariable Long id) {
        ProdutoDto dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findAll(Pageable pageable) {
        Page<ProdutoDto> dto = service.findAll(pageable);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> insert(@RequestBody ProdutoDto dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody ProdutoDto dto) {
        dto = service.update(id,dto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
