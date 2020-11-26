import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { QuestionsSetupComponent } from './../config/questions-setup/questions-setup.component'

const routes: Routes = [
  { path: "", component: QuestionsSetupComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigRoutingModule { }
