import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-categoria-lista',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './categoria-lista.component.html',
  styleUrls: ['./categoria-lista.component.css']
})
export class CategoriaListaComponent {
  categorias: any[] = [];
  novaCategoria: string = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.carregarCategorias();
  }

  carregarCategorias(): void {
    this.categoriaService.listarCategorias().subscribe(data => {
      this.categorias = data;
    });
  }

  adicionarCategoria(): void {
    if (this.novaCategoria.trim()) {
      this.categoriaService.salvarCategoria({ nome: this.novaCategoria }).subscribe(() => {
        this.carregarCategorias();
        this.novaCategoria = '';
      });
    }
  }

  excluirCategoria(id: number): void {
    this.categoriaService.excluirCategoria(id).subscribe(() => {
      this.carregarCategorias();
    });
  }
}