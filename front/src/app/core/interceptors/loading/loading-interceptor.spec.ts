import { TestBed } from "@angular/core/testing"
import { LoadingService } from "../../../shared/loading/loading.service"
import { provideZonelessChangeDetection } from "@angular/core";
import { HttpClient, provideHttpClient, withInterceptors } from "@angular/common/http";
import { loadingInterceptor } from "./loading-interceptor";
import { HttpTestingController, provideHttpClientTesting } from "@angular/common/http/testing";

describe('loadingInterceptor', () => {
    let mockLoadingService: any;
    let http: HttpClient;
    let httpMock: HttpTestingController;

    beforeEach(() => {
        mockLoadingService = {
            show: jasmine.createSpy('show'),
            hide: jasmine.createSpy('hide')
        };
        TestBed.configureTestingModule({
            providers: [
                provideZonelessChangeDetection(),
                { provide: LoadingService, useValue: mockLoadingService },
                provideHttpClient(
                    withInterceptors([loadingInterceptor])
                ),
                provideHttpClientTesting()
            ]
        })
        http = TestBed.inject(HttpClient)
        httpMock = TestBed.inject(HttpTestingController);
    })

    it("Call loading show, when start a HttpRequest", () => {
        http.get('/test').subscribe();
        const req = httpMock.expectOne('/test');

        expect(mockLoadingService.show).toHaveBeenCalledTimes(1)
        req.flush({});
    })
    it("Call loading hide, when completed a HttpRequest", () => {
        http.get('/test').subscribe();
        const req = httpMock.expectOne('/test');

        req.flush({});
        expect(mockLoadingService.hide).toHaveBeenCalledTimes(1)

    })
    it("Call loading show, before call loading hide", () => {
        http.get('/test').subscribe();
        const req = httpMock.expectOne('/test');

        req.flush({});
        expect(mockLoadingService.show).toHaveBeenCalledBefore(mockLoadingService.hide)
    })
})