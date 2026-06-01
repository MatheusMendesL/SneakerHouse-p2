package com.sneakerhouse.service;

import com.sneakerhouse.dto.ProdutoDTO;
import com.sneakerhouse.entity.Produto;
import com.sneakerhouse.exception.ResourceNotFoundException;
import com.sneakerhouse.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarVitrine() {
        return produtoRepository.findByAtivoTrue().stream()
                .map(ProdutoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return listarVitrine();
        }
        return produtoRepository.findByAtivoTrueAndNomeContainingIgnoreCase(nome.trim()).stream()
                .map(ProdutoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorId(Long id) {
        return ProdutoDTO.fromEntity(buscarEntidade(id));
    }

    public Produto buscarEntidade(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!Boolean.TRUE.equals(produto.getAtivo())) {
            throw new ResourceNotFoundException("Produto não disponível");
        }

        return produto;
    }
}
