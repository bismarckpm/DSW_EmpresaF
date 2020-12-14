import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RespondentComponent } from 'src/app/pages/respondent/respondent.component';
import { QuestionsComponent } from 'src/app/pages/questions/questions.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { PollComponent } from 'src/app/pages/poll/poll.component';
import { StudyComponent } from 'src/app/pages/study/study.component';
import { RequestStudyComponent } from "src/app/pages/request-study/request-study.component";

const routes: Routes = [
  { 
    path: "",
    component:GlobalMenuPComponent,
    children: [
    { path: "respondent", component: RespondentComponent},
    { path: "questions", component: QuestionsComponent},
    { path: "client", component: ClientComponent},
    { path: "study", component: StudyComponent},
    { path: "request-study", component: RequestStudyComponent}  
    ],
  },
  { path: 'poll/:id', component: PollComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
