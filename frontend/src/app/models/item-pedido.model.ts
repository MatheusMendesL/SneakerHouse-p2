export interface ItemPedidoRequest {
  produtoId: number;
  quantidade: number;
}

export interface ItemCarrinho {
  produtoId: number;
  nome: string;
  marca: string;
  imagem: string;
  preco: number;
  estoque: number;
  quantidade: number;
}
