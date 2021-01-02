import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';
import { GlobalMenuAComponent } from './global-menuA.component';
import { SampleComponent } from './../analitics/sample/sample.component';
import { UpdateSampleComponent } from './../analitics/update-sample/update-sample.component';
import { InterviewSurveyComponent } from './interview-survey/interview-survey.component';
import { DescriptionCardComponent } from './interview-survey/description-card.component';
import { OptionCardComponent } from './interview-survey/option-card.component';
import { RangeCardComponent } from './interview-survey/range-card.component';
import { MyStudiesComponent } from 'src/app/analitics/my-studies/my-studies.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { PasswordProfileComponent } from './profile/password-profile.component';
import { ProfileComponent } from './profile/profile.component';
import { ResultsComponent } from 'src/app/analitics/results/results.component'

const routes: Routes = [
    {
      path: "",
      component:GlobalMenuAComponent,
      children: [
        { path: "menuanalitics", component: MenuAnaliticsComponent},
        { path: "myStudies", component: MyStudiesComponent},
        { path: "sample", component: SampleComponent},
        { path: "updatesample", component: UpdateSampleComponent},
        { path: "interview", component: InterviewSurveyComponent},
        { path: "card-desc", component: DescriptionCardComponent},
        { path: "card-range", component: RangeCardComponent},
        { path: "card-op", component: OptionCardComponent},
        { path: "analysis", component: AnalysisComponent},
        { path: "password", component: PasswordProfileComponent},
        { path: "profile", component: ProfileComponent},
        { path: "results/:id", component: ResultsComponent},
      ]
    }
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class AnaliticsRoutingModule { }
