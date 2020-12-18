import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { PagesRoutingRespondentModule } from './pages-routing-respondent.module';
import { RespondentComponent } from './respondent/respondent.component';
import { GlobalMenuPComponent } from './global-menuP.component';
import { QuestionsComponent } from './questions/questions.component';
import { PollComponent } from './poll/poll.component';
import { StudyComponent } from './study/study.component';
import { PasswordProfileComponent } from '../pages-respondent/profile/password-profile.component';
import { ProfileComponent } from '../pages-respondent/profile/profile.component';

@NgModule({
  declarations: [RespondentComponent,GlobalMenuPComponent, QuestionsComponent, PollComponent, StudyComponent,PasswordProfileComponent,ProfileComponent],
  imports: [
    PagesRoutingRespondentModule,
    CommonModule,
    SharedModule
  ]
})
export class PagesRespondentModule { }
