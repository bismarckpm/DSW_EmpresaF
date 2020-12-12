import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { AnaliticsRoutingModule } from './analitics-routing.module';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';
import { GlobalMenuAComponent } from './global-menuA.component';
import { SampleComponent } from './../analitics/sample/sample.component';
import { UpdateSampleComponent } from './../analitics/update-sample/update-sample.component';

@NgModule({
    declarations: [MenuAnaliticsComponent,GlobalMenuAComponent,SampleComponent,UpdateSampleComponent
    ],
    imports: [
      AnaliticsRoutingModule,
      CommonModule,
      SharedModule
    ]
  })
  export class AnaliticsModule { }