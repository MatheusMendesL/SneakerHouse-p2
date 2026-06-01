import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ItemCarrinho } from '../../models/item-pedido.model';
import { CarrinhoService } from '../../core/services/carrinho.service';
import { PedidoService } from '../../core/services/pedido.service';
import { AuthService } from '../../core/services/auth.service';

declare const bootstrap: {
  Modal: {
    new (el: HTMLElement): { show: () => void; hide: () => void };
    getInstance(el: HTMLElement): { hide: () => void } | null;
  };
};

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, RouterLink],
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.scss'
})
export class CarrinhoComponent implements OnInit {
  itens: ItemCarrinho[] = [];
  total = 0;
  processando = false;
  erro = '';

  constructor(
    private carrinho: CarrinhoService,
    private pedidoService: PedidoService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carrinho.itens$.subscribe((itens) => {
      this.itens = itens;
      this.total = this.carrinho.total();
    });
  }

  atualizarQtd(item: ItemCarrinho, qtd: number): void {
    this.carrinho.atualizarQuantidade(item.produtoId, qtd);
  }

  remover(produtoId: number): void {
    this.carrinho.remover(produtoId);
  }

  limpar(): void {
    this.carrinho.limpar();
  }

  finalizarPedido(): void {
    if (!this.auth.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    const user = this.auth.getUser();
    if (!user || this.itens.length === 0) return;

    this.processando = true;
    this.erro = '';

    this.pedidoService.criar({
      clienteId: user.id,
      itens: this.itens.map((i) => ({
        produtoId: i.produtoId,
        quantidade: i.quantidade
      }))
    }).subscribe({
      next: () => {
        this.processando = false;
        this.carrinho.limpar();
        const modalEl = document.getElementById('modalPedidoSucesso');
        if (modalEl) {
          const modal = new bootstrap.Modal(modalEl);
          modal.show();
        }
      },
      error: (err) => {
        this.processando = false;
        this.erro = err.error?.message || 'Erro ao finalizar pedido. Tente novamente.';
      }
    });
  }

  irParaHome(): void {
    const modalEl = document.getElementById('modalPedidoSucesso');
    if (modalEl) {
      const modal = bootstrap.Modal.getInstance(modalEl);
      modal?.hide();
    }
    this.router.navigate(['/']);
  }
}
