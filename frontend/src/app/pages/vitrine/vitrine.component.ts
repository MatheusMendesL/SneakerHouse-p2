import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BannerComponent } from '../../shared/components/banner/banner.component';
import { ProductCardComponent } from '../../shared/components/product-card/product-card.component';
import { ProdutoService } from '../../core/services/produto.service';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-vitrine',
  standalone: true,
  imports: [CommonModule, BannerComponent, ProductCardComponent],
  templateUrl: './vitrine.component.html',
  styleUrl: './vitrine.component.scss'
})
export class VitrineComponent implements OnInit {
  produtos: Produto[] = [];
  carregando = true;
  erro = '';

  constructor(private produtoService: ProdutoService) {}

  ngOnInit(): void {
    this.produtoService.vitrine().subscribe({
      next: (data) => {
        this.produtos = data;
        this.carregando = false;
        console.log(this.produtos)
      },
      error: () => {
        this.erro = 'Não foi possível carregar os produtos. Verifique se o backend está rodando.';
        this.carregando = false;
      }
    });
  }
}
