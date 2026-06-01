import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./pages/vitrine/vitrine.component').then(m => m.VitrineComponent) },
  { path: 'busca', loadComponent: () => import('./pages/busca/busca.component').then(m => m.BuscaComponent) },
  { path: 'produto/:id', loadComponent: () => import('./pages/detalhe-produto/detalhe-produto.component').then(m => m.DetalheProdutoComponent) },
  { path: 'carrinho', loadComponent: () => import('./pages/carrinho/carrinho.component').then(m => m.CarrinhoComponent) },
  { path: 'login', loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent) },
  { path: 'cadastro', loadComponent: () => import('./pages/cadastro/cadastro.component').then(m => m.CadastroComponent) },
  { path: 'esqueci-senha', loadComponent: () => import('./pages/esqueci-senha/esqueci-senha.component').then(m => m.EsqueciSenhaComponent) },
  { path: '**', redirectTo: '' }
];
