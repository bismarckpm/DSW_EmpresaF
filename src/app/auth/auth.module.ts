import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from 'src/app/auth/login/login.component'
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthRoutingModule } from  'src/app/auth/auth-routing.module'

@NgModule({
  declarations: [LoginComponent],
  imports: [
    AuthRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class AuthModule { }
