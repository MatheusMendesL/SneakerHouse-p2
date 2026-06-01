import { ItemPedidoRequest } from './item-pedido.model';

export interface PedidoRequest {
  clienteId: number;
  itens: ItemPedidoRequest[];
}

export interface PedidoResponse {
  idPedido: number;
  valorTotal: number;
  status: string;
}
