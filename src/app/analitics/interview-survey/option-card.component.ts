import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-option-card',
  templateUrl: './option-card.component.html',
  styleUrls: ['./interview-survey.component.scss']
})
export class OptionCardComponent implements OnInit {
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idOp' , 'desOp'];
  constructor() { }

  ngOnInit(): void {
    this.getOpciones();
  }

  getOpciones(){

    this.element = [
      {idOp: 7, desOp: 'Primera prueba de opcion'},
      {idOp: 2, desOp: 'But who has any right to find fault with a man who chooses to enjoy a pleasure'},
      {idOp: 3, desOp: 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis'},
      {idOp: 4, desOp: 'laborum et dolorum fuga. Et harum quidem rerum'},
      {idOp: 5, desOp: ' Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit'},
    ];
    this.dataSource = new MatTableDataSource(this.element);
  }

  guardar(idOp){
  console.log(idOp);
  };

}
