import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTooltipModule } from 'ng-zorro-antd/tooltip';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { AuthService } from '../../../service/auth-service';

@Component({
    selector: 'app-logout',
    imports: [
        RouterLink,
        RouterOutlet,
        NzIconModule,
        NzTooltipModule,
        NzModalModule,
        NzButtonModule
    ],
    templateUrl: './logout.html',
    styleUrl: './logout.less'
})
export class Logout {
    logoutModalIsVisible = false

    private authService = inject(AuthService)

    public logout() {
        this.authService.logout();
    }

    public showModal(): void {
        this.logoutModalIsVisible = true;
    }

    public handleCancel() {
        this.logoutModalIsVisible = false;
    }
}
