import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { AnaliticsRoutingModule } from './analitics-routing.module';
import { MenuAnaliticsComponent } from './../analitics/menus/menu-analitics.component';
import { GlobalMenuAComponent } from './global-menuA.component';
import { SampleComponent } from './../analitics/sample/sample.component';
import { UpdateSampleComponent } from './../analitics/update-sample/update-sample.component';
import { InterviewSurveyComponent } from './interview-survey/interview-survey.component';
import { DescriptionCardComponent } from './interview-survey/description-card.component';
import { OptionCardComponent } from './interview-survey/option-card.component';
import { RangeCardComponent } from './interview-survey/range-card.component';
import { MyStudiesComponent } from './my-studies/my-studies.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { PasswordProfileComponent } from './profile/password-profile.component';
import { ProfileComponent } from './profile/profile.component';
import { ResultsComponent } from './results/results.component';;
import { AddSampleComponent } from 'src/app/analitics/addSample/addSample.component';
import { FinishStudyComponent } from './finish-study/finish-study.component'

@NgModule({
    declarations: [MenuAnaliticsComponent,GlobalMenuAComponent,SampleComponent,UpdateSampleComponent,
       InterviewSurveyComponent,  DescriptionCardComponent, OptionCardComponent, RangeCardComponent, AnalysisComponent,
       PasswordProfileComponent,ProfileComponent,MyStudiesComponent, ResultsComponent,AddSampleComponent, FinishStudyComponent
    ],
    imports: [
      AnaliticsRoutingModule,
      CommonModule,
      SharedModule
    ]
  })
  export class AnaliticsModule { }
