import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from '././register/register.component';
import { SharedModule } from './../shared/shared.module';
import { PagesRoutingModule } from './pages-routing.module';

@NgModule({
  declarations: [RegisterComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
