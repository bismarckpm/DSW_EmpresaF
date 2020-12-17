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
import { AnalysisComponent } from './analysis/analysis.component';

@NgModule({
    declarations: [MenuAnaliticsComponent,GlobalMenuAComponent,SampleComponent,UpdateSampleComponent,
       InterviewSurveyComponent,  DescriptionCardComponent, OptionCardComponent, RangeCardComponent, AnalysisComponent
    ],
    imports: [
      AnaliticsRoutingModule,
      CommonModule,
      SharedModule
    ]
  })
  export class AnaliticsModule { }
