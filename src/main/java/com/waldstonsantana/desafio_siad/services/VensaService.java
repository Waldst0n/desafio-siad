package com.waldstonsantana.desafio_siad.services;

import com.waldstonsantana.desafio_siad.dtos.ProdutoDto;
import com.waldstonsantana.desafio_siad.dtos.VendaDto;
import com.waldstonsantana.desafio_siad.models.Fisica;
import com.waldstonsantana.desafio_siad.models.Juridica;
import com.waldstonsantana.desafio_siad.models.Produto;
import com.waldstonsantana.desafio_siad.models.Venda;
import com.waldstonsantana.desafio_siad.repositories.PessoaFisicaRepository;
import com.waldstonsantana.desafio_siad.repositories.ProdutoRepository;
import com.waldstonsantana.desafio_siad.repositories.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VensaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public VendaDto findbyId (Long id) {

        Venda venda = vendaRepository.findById(id).orElseThrow(
                () ->new RuntimeException("Recurso não encontrado!"));

        return new VendaDto(venda);
    }

    @Transactional(readOnly = true)
    public Page<VendaDto> findAll(Pageable pageable) {
        Page<Venda> vendas = vendaRepository.findAll(pageable);

        return vendas.map(x -> new VendaDto(x));
    }

    @Transactional
    public VendaDto insert(@RequestBody VendaDto dto) {

        Venda entity = new Venda();

        copyDtoToEntity(dto,entity);

        Fisica fisica = pessoaFisicaRepository.getReferenceById(dto.getPessoaFisicaDto().getId());
        Produto produto = produtoRepository.getReferenceById(dto.getProdutoDto().getId());

        entity.setFisica(fisica);
        entity.setProduto(produto);

        entity = vendaRepository.save(entity);

        return  new VendaDto(entity);

    }

    @Transactional
    public VendaDto update(Long id, VendaDto dto) {
        try {
            Venda entity = vendaRepository.getReferenceById(id);
            copyDtoToEntity(dto,entity);

            return  new VendaDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new RuntimeException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!vendaRepository.existsById(id)) {
            throw  new RuntimeException("Recurso não encontrado!");
        }
        try {
            vendaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw  new RuntimeException("Falha de integridade referencial");
        }

        vendaRepository.deleteById(id);

    }


    private void copyDtoToEntity(VendaDto dto, Venda entity) {
        entity.setTotal(dto.getTotal());
        entity.setQuantidade(dto.getQuantidade());


    }


}
