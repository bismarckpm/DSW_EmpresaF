import { Component, OnInit, ViewChild } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-interview-survey',
  templateUrl: './interview-survey.component.html',
  styleUrls: ['./interview-survey.component.scss']
})
export class InterviewSurveyComponent implements OnInit {

  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idPE','idEncuesta','idPregunta','tipoPregunta','icono'];
  constructor(public router: Router) { }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getPreguntas();

  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getPreguntas(){

    this.element = [
      {idPE: 1, idEncuesta: 4, idPregunta: 2, tipoPregunta:'desarrollo', icono: true},
      {idPE: 2, idEncuesta: 4, idPregunta: 6, tipoPregunta:'opcion', icono: true},
      {idPE: 3, idEncuesta: 4, idPregunta: 7, tipoPregunta:'desarrollo', icono: true},
      {idPE: 5, idEncuesta: 4, idPregunta: 3, tipoPregunta:'rango', icono: true},
      {idPE: 6, idEncuesta: 4, idPregunta: 11, tipoPregunta:'rango', icono: true},
      {idPE: 7, idEncuesta: 4, idPregunta: 22, tipoPregunta:'opcion', icono: true},
      {idPE: 8, idEncuesta: 4, idPregunta: 4, tipoPregunta:'desarrollo', icono: true},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  interviewAnswer(tipoPregunta, idPregunta, icono){
    console.log(idPregunta,tipoPregunta,icono);

    if(tipoPregunta == 'desarrollo'){
      this.router.navigate(['analitics/card-desc']);
    } else if(tipoPregunta == 'opcion'){
      this.router.navigate(['analitics/card-op']);
    } else if(tipoPregunta == 'rango'){
      this.router.navigate(['analitics/card-range']);
    } else{
      console.log("No eraaaa");
    }
  }


}
