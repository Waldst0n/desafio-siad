package com.waldstonsantana.desafio_siad.services;

import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.models.Juridica;
import com.waldstonsantana.desafio_siad.models.Produto;
import com.waldstonsantana.desafio_siad.repositories.PessoaJuridicaRepository;
import com.waldstonsantana.desafio_siad.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;


    @Autowired
    private PessoaJuridicaRepository juridicaRepository;



    @Transactional(readOnly = true)
    public ProdutoDto findById(Long id) {
        Produto produto = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));

        return new ProdutoDto(produto);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDto> findAll(Pageable pageable) {
        Page<Produto> produtos = repository.findAll(pageable);
        return  produtos.map(x -> new ProdutoDto(x));
    }

    @Transactional
    public ProdutoDto insert(ProdutoDto dto) {
        Produto entity = new Produto();

        copyDtoToEntity(dto,entity);

        Juridica juridica = juridicaRepository.getReferenceById(dto.getPessoaJuridicaDto().getId());
      //  juridica.setId(dto.getPessoaJuridicaDto().getId());

        entity.setJuridica(juridica);

        entity = repository.save(entity);


        return  new ProdutoDto(entity);
    }

    @Transactional
    public ProdutoDto update(Long id, ProdutoDto dto) {
        try {
            Produto entity = repository.getReferenceById(id);
            copyDtoToEntity(dto,entity);

            return  new ProdutoDto(entity);
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



    private void copyDtoToEntity(ProdutoDto dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());


    }


}
