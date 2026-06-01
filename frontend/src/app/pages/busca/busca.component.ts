import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ProductCardComponent } from '../../shared/components/product-card/product-card.component';
import { ProdutoService } from '../../core/services/produto.service';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-busca',
  standalone: true,
  imports: [CommonModule, ProductCardComponent],
  templateUrl: './busca.component.html',
  styleUrl: './busca.component.scss'
})
export class BuscaComponent implements OnInit {
  produtos: Produto[] = [];
  termo = '';
  carregando = true;

  constructor(
    private route: ActivatedRoute,
    private produtoService: ProdutoService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.termo = params['nome'] || '';
      this.buscar();
    });
  }

  buscar(): void {
    this.carregando = true;
    this.produtoService.buscar(this.termo).subscribe({
      next: (data) => {
        this.produtos = data;
        this.carregando = false;
      },
      error: () => {
        this.produtos = [];
        this.carregando = false;
      }
    });
  }
}
