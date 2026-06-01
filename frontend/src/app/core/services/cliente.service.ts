import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Cliente } from '../../models/cliente.model';

@Injectable({ providedIn: 'root' })
export class ClienteService {
  private readonly api = `${environment.apiUrl}/clientes`;

  constructor(private http: HttpClient) {}

  cadastrar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.api, cliente);
  }

  redefinirSenha(email: string, novaSenha: string): Observable<void> {
    return this.http.post<void>(`${this.api}/redefinir-senha`, { email, novaSenha });
  }
}
