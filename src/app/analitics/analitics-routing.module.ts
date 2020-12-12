import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';
import { GlobalMenuAComponent } from './global-menuA.component';
import { SampleComponent } from './../analitics/sample/sample.component';
import { UpdateSampleComponent } from './../analitics/update-sample/update-sample.component';

const routes: Routes = [
    { 
      path: "", 
      component:GlobalMenuAComponent,
      children: [
        { path: "menuanalitics", component: MenuAnaliticsComponent},
        { path: "sample", component: SampleComponent},
        { path: "updatesample", component: UpdateSampleComponent},
      ]
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class AnaliticsRoutingModule { }