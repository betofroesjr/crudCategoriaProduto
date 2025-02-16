import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { authInterceptor } from './auth.interceptor'; // ✅ Importando o interceptor correto

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideHttpClient(withInterceptors([authInterceptor])), provideRouter(routes), provideClientHydration(withEventReplay())]
};