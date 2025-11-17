import { inject, Injectable } from '@angular/core';
import { LoginFormValue } from '../pages/login/interface/loginInterface';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthModel, AuthReturnToken } from '../interfaces/authModel';
import { delay, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private route = inject(Router);

  constructor(private httpCliente: HttpClient) { }

  BASE_API_URL = environment.apiUrl;
  API = "/auth";
  KEY = "token";

  public getToken(): string | null {
    return localStorage.getItem(this.KEY);
  }

  public authenticateLogin(loginForm: LoginFormValue) {
    if (loginForm.username && loginForm.password) {

      const loginData: AuthModel = {
        username: loginForm.username,
        password: loginForm.password
      }

      this.login(loginData)
        .subscribe({
          next: (res) => {
            localStorage.setItem(this.KEY, res.token.toString())
            this.route.navigate(["/welcome"])
          },
          error: (err) => {
            console.error('Erro no login:', err);
            alert('Usuário ou senha inválidos.');
          }
        })

    }
  }

  public logout() {
    localStorage.removeItem(this.KEY);
    this.route.navigate(["/login"])
  }

  private login(login: AuthModel): Observable<AuthReturnToken> {
    return this.httpCliente.post<AuthReturnToken>(
      `${this.BASE_API_URL}${this.API}/login`,
      login
    )
  }

  public isActive(): Observable<any> {
    return this.httpCliente.get(
      `${this.BASE_API_URL}${this.API}/isActive`,
      { observe: 'response' }
    )
  }
}
