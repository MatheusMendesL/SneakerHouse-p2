package com.sneakerhouse.dto;

import com.sneakerhouse.entity.Produto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String marca;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private String imagem;
    private String categoria;
    private Boolean ativo;

    public static ProdutoDTO fromEntity(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .estoque(produto.getEstoque())
                .imagem(produto.getImagem())
                .categoria(produto.getCategoria())
                .ativo(produto.getAtivo())
                .build();
    }
}
