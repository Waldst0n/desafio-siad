package com.waldstonsantana.desafio_siad.services;

import com.waldstonsantana.desafio_siad.dtos.PessoaFisicaDto;
import com.waldstonsantana.desafio_siad.models.Fisica;
import com.waldstonsantana.desafio_siad.repositories.PessoaFisicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository repository;

    @Transactional(readOnly = true)
    public PessoaFisicaDto findById(Long id) {
        Fisica fisica = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));

        return new PessoaFisicaDto(fisica);
    }

    @Transactional(readOnly = true)
    public Page<PessoaFisicaDto> findAll(Pageable pageable) {
        Page<Fisica> pfisicas = repository.findAll(pageable);
        return  pfisicas.map(x -> new PessoaFisicaDto(x));
    }

    @Transactional
    public PessoaFisicaDto insert(PessoaFisicaDto dto) {
        Fisica entity = new Fisica();

        copyDtoToEntity(dto,entity);

        entity = repository.save(entity);

        return  new PessoaFisicaDto(entity);
    }

    @Transactional
    public PessoaFisicaDto update(Long id, PessoaFisicaDto dto) {
        try {
            Fisica entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);

            return  new PessoaFisicaDto(entity);
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



    private void copyDtoToEntity(PessoaFisicaDto dto, Fisica entity) {
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpf(dto.getCpf());


    }

}
