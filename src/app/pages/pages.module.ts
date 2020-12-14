import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { PagesRoutingModule } from './pages-routing.module';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { RequestStudyComponent } from './request-study/request-study.component';

@NgModule({
  declarations: [GlobalMenuPComponent,ClientComponent, RequestStudyComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
