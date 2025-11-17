import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule, NzMenuThemeType } from 'ng-zorro-antd/menu';
import { NzTooltipModule } from 'ng-zorro-antd/tooltip';
import { AuthService } from '../../service/auth-service';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { Logout } from "./logout/logout";

@Component({
  selector: 'app-root',
  imports: [
    RouterLink,
    RouterOutlet,
    NzIconModule,
    NzLayoutModule,
    NzMenuModule,
    NzTooltipModule,
    NzModalModule,
    NzButtonModule,
    Logout
],
  templateUrl: './main.html',
  styleUrl: './main.less'
})
export class Main {
  isCollapsed = false;
  theme: NzMenuThemeType = 'light'
}
