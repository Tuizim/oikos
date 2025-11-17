import { Component, inject } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { AuthService } from '../../../service/auth-service';
import { LoginFormValue } from '../interface/loginInterface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule, NzButtonModule, NzCheckboxModule, NzFormModule, NzInputModule],
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.less']
})

export class LoginFormComponent {
  private authService = inject(AuthService)
  private fb = inject(NonNullableFormBuilder);

  validateForm = this.fb.group({
    username: this.fb.control('', [Validators.required]),
    password: this.fb.control('', [Validators.required]),
    remember: this.fb.control(true)
  });

  submitForm(): void {
    if (this.validateForm.valid) {
      const loginValid = this.validateForm.value as LoginFormValue;
      this.authService.authenticateLogin(loginValid);
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
