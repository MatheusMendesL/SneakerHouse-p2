import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Produto } from '../../models/produto.model';

@Injectable({ providedIn: 'root' })
export class ProdutoService {
  private readonly api = `${environment.apiUrl}/produtos`;

  constructor(private http: HttpClient) {}

  vitrine(): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${this.api}/vitrine`);
  }

  buscar(nome: string): Observable<Produto[]> {
    const params = new HttpParams().set('nome', nome);
    return this.http.get<Produto[]>(`${this.api}/busca`, { params });
  }

  detalhe(id: number): Observable<Produto> {
    return this.http.get<Produto>(`${this.api}/${id}`);
  }
}
