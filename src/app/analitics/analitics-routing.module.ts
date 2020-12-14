import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';
import { GlobalMenuAComponent } from './global-menuA.component';
import { SampleComponent } from './../analitics/sample/sample.component';
import { UpdateSampleComponent } from './../analitics/update-sample/update-sample.component';
import { InterviewSurveyComponent } from './interview-survey/interview-survey.component';
import { DescriptionCardComponent } from './interview-survey/description-card.component';
import { FeedbackComponent } from './menus/feedback/feedback.component';

const routes: Routes = [
    {
      path: "",
      component:GlobalMenuAComponent,
      children: [
        { path: "menuanalitics", component: MenuAnaliticsComponent},
        { path: "sample", component: SampleComponent},
        { path: "updatesample", component: UpdateSampleComponent},
        { path: "interview", component: InterviewSurveyComponent},
        { path: "card-desc", component: DescriptionCardComponent},
        { path: "feedback", component: FeedbackComponent}
      ]
    }
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class AnaliticsRoutingModule { }
