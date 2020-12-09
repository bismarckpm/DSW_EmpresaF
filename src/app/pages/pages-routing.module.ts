import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RespondentComponent } from 'src/app/pages/respondent/respondent.component';
import { SurveyComponent } from 'src/app/pages/survey/survey.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { StudyRquestComponent } from './study-request/study-request.component';


const routes: Routes = [
  { 
    path: "",
    component:GlobalMenuPComponent,
    children: [
    { path: "respondent", component: RespondentComponent},
    { path: "survery", component: SurveyComponent},
    { path: "client", component: ClientComponent},
    { path: "studyrequest", component: StudyRquestComponent},
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
