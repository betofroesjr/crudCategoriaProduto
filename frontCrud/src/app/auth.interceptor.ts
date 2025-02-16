import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwtToken'); // 🔹 Obtém o token JWT

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}` // 🔹 Adiciona o token no cabeçalho
      }
    });
    return next(cloned);
  }

  return next(req);
};