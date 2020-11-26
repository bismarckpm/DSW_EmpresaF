import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from 'src/app/auth/login/login.component'
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthRoutingModule } from  'src/app/auth/auth-routing.module';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component'

@NgModule({
  declarations: [LoginComponent, ForgotPasswordComponent],
  imports: [
    AuthRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class AuthModule { }
