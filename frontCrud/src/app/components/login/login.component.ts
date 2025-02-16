import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {  
  errorMessage: string = '';
  user = { username: '', password: '' };

  constructor(private authService: AuthService, private router:Router) {}

  login(): void {
    this.authService.login(this.user.username, this.user.password).subscribe({
      next: (data) => {
        localStorage.setItem('jwtToken', data.token); // ✅ Armazena o token
        console.log('Login bem-sucedido!');
        this.router.navigate(['/dashboard']); // ✅ Redireciona para Dashboard
      },
      error: () => {
        this.errorMessage = 'Credenciais inválidas';
      }
    });
  }
}