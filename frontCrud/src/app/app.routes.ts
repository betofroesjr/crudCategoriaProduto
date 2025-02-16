import { Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
    { path: '', component: LoginComponent }, // Rota padrão para login
    { path: 'dashboard', component: DashboardComponent } // Tela para onde redirecionar após login
  ];