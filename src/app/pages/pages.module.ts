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
import { StudyRquestComponent } from 'src/app/pages/study-request/study-request.component';

@NgModule({
  declarations: [RespondentComponent,GlobalMenuPComponent,ClientComponent, QuestionsComponent, PollComponent, StudyComponent,StudyRquestComponent],
  imports: [
    PagesRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesModule { }
