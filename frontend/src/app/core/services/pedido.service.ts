import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PedidoRequest, PedidoResponse } from '../../models/pedido.model';

@Injectable({ providedIn: 'root' })
export class PedidoService {
  private readonly api = `${environment.apiUrl}/pedidos`;

  constructor(private http: HttpClient) {}

  criar(pedido: PedidoRequest): Observable<PedidoResponse> {
    return this.http.post<PedidoResponse>(this.api, pedido);
  }
}
