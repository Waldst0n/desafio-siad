package com.waldstonsantana.desafio_siad.controllers;

import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.dtos.VendaDto;
import com.waldstonsantana.desafio_siad.services.VensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

    @Autowired
    private VensaService vensaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDto> findById(@PathVariable Long id) {
        VendaDto dto = vensaService.findbyId(id);

        return  ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<VendaDto>> findAll (Pageable pageable) {
        Page<VendaDto> dto = vensaService.findAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VendaDto> insert(@RequestBody VendaDto dto) {
        dto = vensaService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VendaDto> update(@PathVariable Long id, @RequestBody VendaDto dto) {
        dto = vensaService.update(id,dto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vensaService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
