import { TestBed } from "@angular/core/testing";
import { provideRouter, Router } from "@angular/router";
import { AuthService } from "../../service/auth-service";
import { authGuard } from "./auth-guard";
import { Login } from "../../pages/login/login";
import { Welcome } from "../../pages/layouts/welcome/welcome";
import { of } from "rxjs";
import { RouterTestingHarness } from "@angular/router/testing";
import { provideHttpClientTesting } from "@angular/common/http/testing";

describe('authGuard', () => {
    let mockAuthService: jasmine.SpyObj<AuthService>;
    let router: Router;

    beforeEach(async () => {
        mockAuthService = jasmine.createSpyObj('AuthService', ['getToken', 'isActive', 'logout']);

        await TestBed.configureTestingModule({
            providers: [
                provideRouter([
                    { path: 'login', component: Login },
                    { path: '', component: Welcome, canActivate: [authGuard] },
                ]),
                provideHttpClientTesting(),
                { provide: AuthService, useValue: mockAuthService }
            ]
        }).compileComponents();

        router = TestBed.inject(Router);
    });

    it('Should approve the route and return true', async () => {
        mockAuthService.getToken.and.returnValue("Token");
        mockAuthService.isActive.and.returnValue(of({ status: 200 }));

        const harness = await RouterTestingHarness.create();
        await harness.navigateByUrl('/');

        expect(router.url).toBe('/');
    });

    it('Should block the route when token is inactive, returning false', async () => {
        mockAuthService.getToken.and.returnValue("Token");
        mockAuthService.isActive.and.returnValue(of({ status: 401 }));

        const harness = await RouterTestingHarness.create();
        await harness.navigateByUrl('/');

        expect(router.url).toBe('/login');
    });

    it('Should redirect to /login if token is missing or not active', async () => {
        mockAuthService.getToken.and.returnValue(null);

        const harness = await RouterTestingHarness.create();
        await harness.navigateByUrl('/');
        expect(router.url).toBe('/login');
    });
});