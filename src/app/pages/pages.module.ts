import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { PagesRoutingModule } from './pages-routing.module';
import { RespondentComponent } from 'src/app/pages/respondent/respondent.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { ClientComponent } from './client/client.component';
import { QuestionsComponent } from './questions/questions.component';
import { PollComponent } from './poll/poll.component';
import { StudyComponent } from './study/study.component';
import { RequestStudyComponent } from './request-study/request-study.component';

@NgModule({
  declarations: [RespondentComponent,GlobalMenuPComponent,ClientComponent, QuestionsComponent, PollComponent, StudyComponent, RequestStudyComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
