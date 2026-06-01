package com.sneakerhouse.config;

import com.sneakerhouse.entity.Produto;
import com.sneakerhouse.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DataInitializer(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }

        List<Produto> produtos = List.of(
                produto(
                        "Nike Air Max 90",
                        "Nike",
                        "Clássico com amortecimento Air visível.",
                        "599.90",
                        25,
                        "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=600",
                        "Running"
                ),
                produto(
                        "Puma smash",
                        "Puma",
                        "Tecnologia Boost para máximo retorno de energia.",
                        "899.90",
                        15,
                        "https://images.unsplash.com/photo-1608231387042-66d1773070a5?w=600",
                        "Running"
                ),
                produto(
                        "New Balance 574",
                        "New Balance",
                        "Herança running com estilo casual.",
                        "499.90",
                        28,
                        "https://images.unsplash.com/photo-1539185441755-769473a23570?w=600",
                        "Casual"
                )
        );

        produtoRepository.saveAll(produtos);
    }

    private Produto produto(
            String nome,
            String marca,
            String descricao,
            String preco,
            int estoque,
            String imagem,
            String categoria
    ) {
        return Produto.builder()
                .nome(nome)
                .marca(marca)
                .descricao(descricao)
                .preco(new BigDecimal(preco))
                .estoque(estoque)
                .imagem(imagem)
                .categoria(categoria)
                .ativo(true)
                .build();
    }
}