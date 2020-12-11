import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';



@Component({
  selector: 'app-update-poll',
  templateUrl: './updatePoll.component.html',
  styleUrls: ['./update.component.scss']
})


export class UpdatePollComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idPregunta', 'descripcionPregunta','icons'];
  constructor(private router: Router) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getQuestions();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getQuestions(){
    
    this.element = [
      {idPregunta: 1, descripcionPregunta: 'Salud'},
      {idPregunta: 2, descripcionPregunta: 'Comidas'},
      {idPregunta: 3, descripcionPregunta: 'Aseo'},
      {idPregunta: 4, descripcionPregunta: 'Oficina'},
      {idPregunta: 5, descripcionPregunta: 'Escolar'},
      {idPregunta: 6, descripcionPregunta: 'Cocina'},
      {idPregunta: 7, descripcionPregunta: 'Muebles'},
      {idPregunta: 8, descripcionPregunta: 'Computadoras'},
      {idPregunta: 9, descripcionPregunta: 'Higiene'},
      {idPregunta: 10, descripcionPregunta: 'Mascotas'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteQuestion(idPregunta){
    console.log(idPregunta)
  }

  updateQuestion(idPregunta){
    console.log(idPregunta)
  }

  addQuestion(){
    this.router.navigate(['/config/addQuestion']);
    console.log('Add Question')
  }
}