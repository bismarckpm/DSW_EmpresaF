import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-study-request',
  templateUrl: './study-request.component.html',
  styleUrls: ['./study-request.component.scss']
})
export class StudyRquestComponent implements OnInit {
  subcategorys: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.subcategorys = 
    [
      { name: 'Cuidado personal'},
      { name: 'Shampo' },
      { name: 'Detergene'}
    ]
  }

}
