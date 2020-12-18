import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { RequestStudyComponent } from "src/app/pages/request-study/request-study.component";
import { ProfileComponent } from './profile/profile.component';
import { PasswordProfileComponent } from './profile/password-profile.component';

const routes: Routes = [
  { 
    path: "",
    component:GlobalMenuPComponent,
    children: [
    { path: "client", component: ClientComponent},
    { path: "request-study", component: RequestStudyComponent},
    { path: "profile", component: ProfileComponent},
    { path: "password", component: PasswordProfileComponent} 
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
