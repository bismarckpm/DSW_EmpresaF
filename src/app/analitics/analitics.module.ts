import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { AnaliticsRoutingModule } from './analitics-routing.module';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';

@NgModule({
    declarations: [MenuAnaliticsComponent
    ],
    imports: [
      AnaliticsRoutingModule,
      CommonModule,
      SharedModule
    ]
  })
  export class AnaliticsModule { }