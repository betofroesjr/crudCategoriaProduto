import { Component } from '@angular/core';
import { CategoriaListaComponent } from '../categoria-lista/categoria-lista.component';
import { ProdutoListaComponent } from '../produto-lista/produto-lista.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CategoriaListaComponent, ProdutoListaComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
