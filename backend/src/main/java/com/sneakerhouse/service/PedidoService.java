package com.sneakerhouse.service;

import com.sneakerhouse.dto.PedidoRequest;
import com.sneakerhouse.dto.PedidoResponse;
import com.sneakerhouse.entity.*;
import com.sneakerhouse.exception.BusinessException;
import com.sneakerhouse.repository.PedidoRepository;
import com.sneakerhouse.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public PedidoResponse criarPedido(PedidoRequest request) {
        Cliente cliente = clienteService.buscarEntidade(request.getClienteId());

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .status(StatusPedido.REALIZADO)
                .valorTotal(BigDecimal.ZERO)
                .itens(new ArrayList<>())
                .build();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (var itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado: " + itemRequest.getProdutoId()));

            if (!Boolean.TRUE.equals(produto.getAtivo())) {
                throw new BusinessException("Produto indisponível: " + produto.getNome());
            }

            if (produto.getEstoque() < itemRequest.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            BigDecimal precoUnitario = produto.getPreco();
            BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(itemRequest.getQuantidade()));

            ItemPedido item = ItemPedido.builder()
                    .pedido(pedido)
                    .produto(produto)
                    .quantidade(itemRequest.getQuantidade())
                    .precoUnitario(precoUnitario)
                    .subtotal(subtotal)
                    .build();

            pedido.getItens().add(item);
            valorTotal = valorTotal.add(subtotal);

            produto.setEstoque(produto.getEstoque() - itemRequest.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setValorTotal(valorTotal);
        Pedido salvo = pedidoRepository.save(pedido);

        return PedidoResponse.builder()
                .idPedido(salvo.getId())
                .valorTotal(salvo.getValorTotal())
                .status(salvo.getStatus())
                .build();
    }
}
