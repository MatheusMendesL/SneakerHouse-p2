import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ItemCarrinho } from '../../models/item-pedido.model';
import { Produto } from '../../models/produto.model';

const CARRINHO_KEY = 'sneakerhouse_carrinho';

@Injectable({ providedIn: 'root' })
export class CarrinhoService {
  private itensSubject = new BehaviorSubject<ItemCarrinho[]>(this.carregar());
  itens$ = this.itensSubject.asObservable();

  private carregar(): ItemCarrinho[] {
    const raw = localStorage.getItem(CARRINHO_KEY);
    return raw ? JSON.parse(raw) : [];
  }

  private salvar(itens: ItemCarrinho[]): void {
    localStorage.setItem(CARRINHO_KEY, JSON.stringify(itens));
    this.itensSubject.next(itens);
  }

  getItens(): ItemCarrinho[] {
    return this.itensSubject.value;
  }

  adicionar(produto: Produto, quantidade: number): void {
    const itens = [...this.getItens()];
    const idx = itens.findIndex((i) => i.produtoId === produto.id);

    if (idx >= 0) {
      const novaQtd = Math.min(itens[idx].quantidade + quantidade, produto.estoque);
      itens[idx] = { ...itens[idx], quantidade: novaQtd };
    } else {
      itens.push({
        produtoId: produto.id,
        nome: produto.nome,
        marca: produto.marca,
        imagem: produto.imagem,
        preco: produto.preco,
        estoque: produto.estoque,
        quantidade: Math.min(quantidade, produto.estoque)
      });
    }

    this.salvar(itens);
  }

  atualizarQuantidade(produtoId: number, quantidade: number): void {
    const itens = this.getItens().map((item) => {
      if (item.produtoId !== produtoId) return item;
      const qtd = Math.max(1, Math.min(quantidade, item.estoque));
      return { ...item, quantidade: qtd };
    });
    this.salvar(itens);
  }

  remover(produtoId: number): void {
    this.salvar(this.getItens().filter((i) => i.produtoId !== produtoId));
  }

  limpar(): void {
    this.salvar([]);
  }

  total(): number {
    return this.getItens().reduce((acc, item) => acc + item.preco * item.quantidade, 0);
  }

  quantidadeItens(): number {
    return this.getItens().reduce((acc, item) => acc + item.quantidade, 0);
  }
}
