package com.sneakerhouse.controller;

import com.sneakerhouse.dto.ProdutoDTO;
import com.sneakerhouse.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/vitrine")
    @Operation(summary = "Listar produtos da vitrine")
    public ResponseEntity<List<ProdutoDTO>> vitrine() {
        return ResponseEntity.ok(produtoService.listarVitrine());
    }

    @GetMapping("/busca")
    @Operation(summary = "Buscar produtos por nome")
    public ResponseEntity<List<ProdutoDTO>> busca(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(produtoService.buscarPorNome(nome));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhes do produto")
    public ResponseEntity<ProdutoDTO> detalhe(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
}
