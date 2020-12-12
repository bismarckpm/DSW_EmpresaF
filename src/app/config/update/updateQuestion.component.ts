import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-question',
  templateUrl: './updateQuestion.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateQuestionComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = 
    {descripcionPregunta: 'Hydrogen', tipo: 'liso'}
    
  }

  updateQuestion(){
      console.log('Update')
  }
}
