package com.waldstonsantana.desafio_siad.services;

import com.waldstonsantana.desafio_siad.dtos.EnderecoDto;
import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.models.Endereco;
import com.waldstonsantana.desafio_siad.models.Fisica;
import com.waldstonsantana.desafio_siad.models.Juridica;
import com.waldstonsantana.desafio_siad.models.Produto;
import com.waldstonsantana.desafio_siad.repositories.EnderecoRepository;
import com.waldstonsantana.desafio_siad.repositories.PessoaFisicaRepository;
import com.waldstonsantana.desafio_siad.services.exceptions.DataBaseException;
import com.waldstonsantana.desafio_siad.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;


    @Transactional(readOnly = true)
    public EnderecoDto findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));

        return new EnderecoDto(endereco);
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDto> findAll(Pageable pageable) {
        Page<Endereco> enderecos = enderecoRepository.findAll(pageable);
        return  enderecos.map(x -> new EnderecoDto(x));
    }

    @Transactional
    public EnderecoDto insert(EnderecoDto dto) {
        Endereco entity = new Endereco();

        copyDtoToEntity(dto,entity);

        Fisica fisica = pessoaFisicaRepository.getReferenceById(dto.getPessoaFisicaDto().getId());

        entity.setFisica(fisica);
        entity = enderecoRepository.save(entity);


        return  new EnderecoDto(entity);
    }

    @Transactional
    public EnderecoDto update(Long id, EnderecoDto dto) {
        try {
            Endereco entity = enderecoRepository.getReferenceById(id);
            copyDtoToEntity(dto,entity);

            return  new EnderecoDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!enderecoRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Recurso não encontrado!");
        }
        try {
            enderecoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw  new DataBaseException("Falha de integridade referencial");
        }

        enderecoRepository.deleteById(id);

    }




    private void copyDtoToEntity(EnderecoDto dto, Endereco entity) {
        entity.setCep(dto.getCep());
        entity.setCidade(dto.getCidade());
        entity.setBairro(dto.getBairro());
        entity.setRua(dto.getRua());
        entity.setNumero(dto.getNumero());

    }
}
