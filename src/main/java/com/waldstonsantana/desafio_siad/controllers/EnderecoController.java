package com.waldstonsantana.desafio_siad.controllers;

import com.waldstonsantana.desafio_siad.dtos.EnderecoDto;
import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoDto> findbyId (@PathVariable Long id) {
        EnderecoDto dto = enderecoService.findById(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDto>> findAll(Pageable pageable) {
        Page<EnderecoDto> dto = enderecoService.findAll(pageable);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> insert(@RequestBody EnderecoDto dto) {
        dto = enderecoService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoDto> update(@PathVariable Long id, @RequestBody EnderecoDto dto) {
        dto = enderecoService.update(id,dto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
