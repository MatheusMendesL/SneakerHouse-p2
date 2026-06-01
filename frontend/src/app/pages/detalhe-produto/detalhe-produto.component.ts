import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProdutoService } from '../../core/services/produto.service';
import { CarrinhoService } from '../../core/services/carrinho.service';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-detalhe-produto',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, RouterLink, FormsModule],
  templateUrl: './detalhe-produto.component.html',
  styleUrl: './detalhe-produto.component.scss'
})
export class DetalheProdutoComponent implements OnInit {
  produto?: Produto;
  quantidade = 1;
  mensagem = '';
  carregando = true;

  constructor(
    private route: ActivatedRoute,
    private produtoService: ProdutoService,
    private carrinho: CarrinhoService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.produtoService.detalhe(id).subscribe({
      next: (data) => {
        this.produto = data;
        this.carregando = false;
      },
      error: () => {
        this.carregando = false;
      }
    });
  }

  adicionarCarrinho(): void {
    if (!this.produto) return;
    this.carrinho.adicionar(this.produto, this.quantidade);
    this.mensagem = 'Produto adicionado ao carrinho!';
    setTimeout(() => (this.mensagem = ''), 3000);
  }

  aumentar(): void {
    if (this.produto && this.quantidade < this.produto.estoque) {
      this.quantidade++;
    }
  }

  diminuir(): void {
    if (this.quantidade > 1) {
      this.quantidade--;
    }
  }
}
