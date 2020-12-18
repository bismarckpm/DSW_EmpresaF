import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { PagesRoutingModule } from './pages-routing.module';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { RequestStudyComponent } from './request-study/request-study.component';
import { ProfileComponent } from './profile/profile.component';
import { PasswordProfileComponent } from './profile/password-profile.component';

@NgModule({
  declarations: [GlobalMenuPComponent,ClientComponent, RequestStudyComponent,PasswordProfileComponent,ProfileComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
