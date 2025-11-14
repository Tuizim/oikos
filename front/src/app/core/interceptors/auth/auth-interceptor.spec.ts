import { TestBed } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './auth-interceptor';
import { AuthService } from '../../../service/auth-service';
import { provideZonelessChangeDetection } from '@angular/core';

describe('authInterceptor', () => {
  let http: HttpClient;
  let httpMock: HttpTestingController;
  let mockAuthService: any;

  beforeEach(() => {
    mockAuthService = {
      getToken: jasmine.createSpy('getToken').and.returnValue('mocked-token'),
    };
    TestBed.configureTestingModule({
      providers: [
        provideZonelessChangeDetection(),
        { provide: AuthService, useValue: mockAuthService },
        provideHttpClient(
          withInterceptors([authInterceptor])
        ),
        provideHttpClientTesting()
      ]
    });

    http = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('deve adicionar Authorization, quando possuir token', () => {
    http.get('/test').subscribe();
    const req = httpMock.expectOne('/test');

    expect(req.request.headers.get('Authorization')).toBe('Bearer mocked-token');

    req.flush({});
  });
  it('não deve adicionar Authorization, quando não possuir token', () => {
    mockAuthService.getToken.and.returnValue(undefined)
    
    http.get('/test').subscribe();
    const req = httpMock.expectOne('/test')

    expect(req.request.headers.get('Authorization')).toBe(null)
  })
});
