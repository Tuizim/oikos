import { Component, inject, signal } from '@angular/core';
import { LoginFormComponent } from "./login-form/login-form.component";
import { Loading } from "../../shared/loading/loading";
import { LoadingService } from '../../shared/loading/loading.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [LoginFormComponent, Loading, AsyncPipe],
  templateUrl: './login.html',
  styleUrl: './login.less',
})
export class Login {
  constructor(public loadingService: LoadingService) {}
}
