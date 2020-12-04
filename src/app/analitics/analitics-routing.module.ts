import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';

const routes: Routes = [
    { 
      path: "", 
      children: [
        { path: "menuanalitics", component: MenuAnaliticsComponent}
      ]
    }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class AnaliticsRoutingModule { }