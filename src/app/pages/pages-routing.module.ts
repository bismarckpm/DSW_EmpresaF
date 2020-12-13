import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RespondentComponent } from 'src/app/pages/respondent/respondent.component';
import { QuestionsComponent } from 'src/app/pages/questions/questions.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { PollComponent } from 'src/app/pages/poll/poll.component';
import { StudyComponent } from 'src/app/pages/study/study.component';
import { StudyRquestComponent } from 'src/app/pages/study-request/study-request.component';


const routes: Routes = [
  { 
    path: "",
    component:GlobalMenuPComponent,
    children: [
    { path: "respondent", component: RespondentComponent},
    { path: "questions", component: QuestionsComponent},
    { path: "client", component: ClientComponent},
    { path: "study", component: PollComponent} ,
    { path: "studyrequest", component: StudyRquestComponent} 
    ],
  },
  { path: 'poll/:id', component: PollComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
