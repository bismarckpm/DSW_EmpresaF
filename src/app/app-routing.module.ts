import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminGuard } from 'src/app/shared/guards/admin.guard';
import { UsersGuard } from 'src/app/shared/guards/users.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: 'full'
  },
  {
    path: 'analitics',
    loadChildren: () => import('./analitics/analitics.module').then(m => m.AnaliticsModule),
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
  },
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule),
    canActivate: [UsersGuard]
  },
  {
    path: 'pages-respondent',
    loadChildren: () => import('./pages-respondent/pages-respondent.module').then(m => m.PagesRespondentModule),
  },
  {
    path: 'config',
    loadChildren: () => import('./config/config.module').then(m => m.ConfigModule),
    canActivate: [AdminGuard]
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
