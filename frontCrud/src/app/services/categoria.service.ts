import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, tap } from 'rxjs';
import { HTTP_OPTIONS } from '../http-options';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiUrl = 'http://localhost:8080/categorias';
  private categoriaAtualizada = new Subject<void>(); // 🔹 Evento para notificar mudança

  constructor(private http: HttpClient) {}

  listarCategorias(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, HTTP_OPTIONS);
  }

  salvarCategoria(categoria: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, categoria, HTTP_OPTIONS).pipe(
      tap(() => {
        this.categoriaAtualizada.next(); // 🔹 Notifica atualização
      })
    );
  }

  excluirCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, HTTP_OPTIONS).pipe(
      tap(() => {
        this.categoriaAtualizada.next(); // 🔹 Notifica atualização
      })
    );
  }

  getCategoriaAtualizada(): Observable<void> {
    return this.categoriaAtualizada.asObservable();
  }
}