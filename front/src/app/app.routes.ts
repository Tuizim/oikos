import { Routes } from '@angular/router';
import { authGuard } from './core/guard/auth-guard';
import { Login } from './pages/login/login';

export const routes: Routes = [
 {
    path: '',
    canActivate: [authGuard],
    loadChildren: () =>
      import('./pages/main/main.routes').then(m => m.MAIN_ROUTES),
  },
  {
    path:'login',
    component:Login
  },
  {
    path: '**',
    redirectTo: '',
  },
];