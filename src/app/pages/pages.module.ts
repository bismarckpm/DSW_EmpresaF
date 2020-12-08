import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { PagesRoutingModule } from './pages-routing.module';
import { SurveyComponent } from './survey/survey.component';
import { RespondentComponent } from 'src/app/pages/respondent/respondent.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';


@NgModule({
  declarations: [SurveyComponent,RespondentComponent,GlobalMenuPComponent,ClientComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
