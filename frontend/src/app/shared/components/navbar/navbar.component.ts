import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { CarrinhoService } from '../../../core/services/carrinho.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {
  termoBusca = '';
  qtdCarrinho = 0;
  usuario: { nome: string } | null = null;

  constructor(
    public auth: AuthService,
    private carrinho: CarrinhoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.usuario = this.auth.getUser();
    this.carrinho.itens$.subscribe(() => {
      this.qtdCarrinho = this.carrinho.quantidadeItens();
    });
  }

  buscar(): void {
    const nome = this.termoBusca.trim();
    if (nome) {
      this.router.navigate(['/busca'], { queryParams: { nome } });
    }
  }

  sair(): void {
    this.auth.logout();
  }
}
