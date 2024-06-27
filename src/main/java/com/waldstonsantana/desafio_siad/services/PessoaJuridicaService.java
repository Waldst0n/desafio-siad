package com.waldstonsantana.desafio_siad.services;

import com.waldstonsantana.desafio_siad.dtos.PessoaJuridicaDto;
import com.waldstonsantana.desafio_siad.models.Juridica;
import com.waldstonsantana.desafio_siad.repositories.PessoaJuridicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository repository;

    @Transactional(readOnly = true)
    public PessoaJuridicaDto findById(Long id){
        Juridica entity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado!"));

        return new PessoaJuridicaDto(entity);

    }

    @Transactional(readOnly = true)
    public Page<PessoaJuridicaDto> findAll(Pageable pageable) {
        Page<Juridica> pjuridicas = repository.findAll(pageable);

        return pjuridicas.map(x -> new PessoaJuridicaDto(x));
    }

    @Transactional
    public PessoaJuridicaDto insert (PessoaJuridicaDto dto) {
        Juridica entity = new Juridica();

        copyDtoToEntity(dto,entity);

        entity = repository.save(entity);

        return new PessoaJuridicaDto(entity);
    }

    @Transactional
    public PessoaJuridicaDto update(Long id, PessoaJuridicaDto dto) {
        try {
            Juridica entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);

            return  new PessoaJuridicaDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new RuntimeException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw  new RuntimeException("Recurso não encontrado!");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw  new RuntimeException("Falha de integridade referencial");
        }

        repository.deleteById(id);

    }

    private void copyDtoToEntity(PessoaJuridicaDto dto, Juridica entity) {
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());


    }
}
