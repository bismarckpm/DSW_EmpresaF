import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestionsSetupComponent } from './../config/questions-setup/questions-setup.component';
import { SharedModule } from './../shared/shared.module';
import { ConfigRoutingModule } from './config-routing.module';

@NgModule({
  declarations: [QuestionsSetupComponent],
  imports: [
    ConfigRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class ConfigModule { }
