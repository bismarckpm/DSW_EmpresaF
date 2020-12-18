import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RespondentComponent } from '../pages-respondent/respondent/respondent.component';
import { QuestionsComponent } from '../pages-respondent/questions/questions.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { PollComponent } from '../pages-respondent/poll/poll.component';
import { StudyComponent } from '../pages-respondent/study/study.component';

const routes: Routes = [
  { 
    path: "",
    component:GlobalMenuPComponent,
    children: [
    { path: "respondent", component: RespondentComponent},
    { path: "questions/:id", component: QuestionsComponent},
    { path: "study", component: StudyComponent},
    { path: 'poll/:id', component: PollComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingRespondentModule { }
