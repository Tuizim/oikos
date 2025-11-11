import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';
import { map } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router)
  const authService = inject(AuthService)

  if (authService.getToken()) {
    return authService.isActive().pipe(
      map(response => {
        if (response.status === 200) {
          return true;
        } else {
          authService.logout();
          router.navigate(['/login']);
          return false;
        }
      }),
    );
  }

  return router.createUrlTree(['/login'])
};
