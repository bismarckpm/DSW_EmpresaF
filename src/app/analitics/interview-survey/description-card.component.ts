import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-description-card',
  templateUrl: './description-card.component.html',
  styleUrls: ['./interview-survey.component.scss']
})
export class DescriptionCardComponent implements OnInit {
   @Input() pregunta: any;

  constructor() { }

  ngOnInit(): void {
  }



}
