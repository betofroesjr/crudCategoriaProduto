import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProdutoService } from '../../services/produto.service';
import { CategoriaService } from '../../services/categoria.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-produto-lista',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './produto-lista.component.html',
  styleUrls: ['./produto-lista.component.css']
})
export class ProdutoListaComponent {
  produtos: any[] = [];
  categoriaSub: Subscription | undefined;
  categorias: any[] = [];
  novoProduto = { nome: '', preco: 0, categoriaId: null };

  constructor(private produtoService: ProdutoService, private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.carregarProdutos();
    this.carregarCategorias();

    // 🔹 Ouve mudanças nas categorias e recarrega os produtos
    this.categoriaSub = this.categoriaService.getCategoriaAtualizada().subscribe(() => {
      this.carregarCategorias();
    });
  }

  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe(data => {
      this.produtos = data;
    });
  }

  carregarCategorias(): void {
    this.categoriaService.listarCategorias().subscribe(data => {
      this.categorias = data;
    });
  }

  adicionarProduto(): void {
    if (this.novoProduto.nome && this.novoProduto.preco && this.novoProduto.categoriaId) {
      this.produtoService.salvarProduto(this.novoProduto.categoriaId, this.novoProduto).subscribe(() => {
        this.carregarProdutos();
        this.novoProduto = { nome: '', preco: 0, categoriaId: null };
      });
    }
  }

  excluirProduto(id: number): void {
    this.produtoService.excluirProduto(id).subscribe(() => {
      this.carregarProdutos();
    });
  }

  ngOnDestroy(): void {
    if (this.categoriaSub) {
      this.categoriaSub.unsubscribe(); // 🔹 Evita vazamento de memória
    }
  }
}
