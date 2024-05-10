import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LayoutComponent } from './layouts/layout.component';
import { CyptolandingComponent } from './cyptolanding/cyptolanding.component';
import { Page404Component } from './extrapages/page404/page404.component';
import { guardGuard } from './auth/guard/guard.guard';

const routes: Routes = [
  // tslint:disable-next-line: max-line-length
  { path: 'dash', component: LayoutComponent, loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule) ,canActivate:[guardGuard]},
  { path: 'crypto-ico-landing', component: CyptolandingComponent },
  {path:'auth',loadChildren:()=>import('./auth/auth.module' ).then(m=>m.AuthModule)},
  {path:'ressSalle',loadChildren:()=>import('./reservation-salle/reservation-salle.module' ).then(m=>m.ReservationSalleModule),canActivate:[guardGuard]},
  {path:'material',loadChildren:()=>import('./material/material.module').then(m=>m.MaterialModule),canActivate:[guardGuard]},
  {path:'respo',component: LayoutComponent,loadChildren:()=>import('./dash-responsable/dash-responsable.module').then(m=>m.DashResponsableModule)},
  {
    path:'',redirectTo:'auth/login',pathMatch:'full',
  },
  { path: '**', component: Page404Component },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'top' })],
  exports: [RouterModule]
})

export class AppRoutingModule { }
