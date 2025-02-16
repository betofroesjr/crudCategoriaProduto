import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HTTP_OPTIONS } from '../http-options'; // Importando os headers

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8080/produtos';

  constructor(private http: HttpClient) { }

  listarProdutos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, HTTP_OPTIONS);
  }

  listarPorCategoria(categoriaId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/categoria/${categoriaId}`, HTTP_OPTIONS);
  }

  salvarProduto(categoriaId: number, produto: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/categoria/${categoriaId}`, produto, HTTP_OPTIONS);
  }

  excluirProduto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, HTTP_OPTIONS);
  }
}