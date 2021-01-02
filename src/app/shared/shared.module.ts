import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule  } from '@angular/forms';
import { MaterialModule } from './material/material.module';
import { HighchartsChartModule } from 'highcharts-angular';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    HighchartsChartModule
  ],
  exports: [
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    HighchartsChartModule
  ]
})
export class SharedModule { }
