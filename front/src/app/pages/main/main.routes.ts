import { Routes } from "@angular/router";
import { Welcome } from "../layouts/welcome/welcome";
import { Monitor } from "../layouts/monitor/monitor";
import { Main } from "./main";
import { authGuard } from "../../core/guard/auth-guard";

export const MAIN_ROUTES: Routes = [
  {
    path: '',
    component: Main,
    children: [
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: 'welcome', component: Welcome, canActivate: [authGuard] },
      { path: 'monitor', component: Monitor, canActivate: [authGuard] },
    ],
  },
];