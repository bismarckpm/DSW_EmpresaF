import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-question',
  templateUrl: './menu-question.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuCQuestionComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idPregunta', 'descripcionPregunta', 'tipo','icons'];
  
  constructor(private router: Router) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMarcas();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getMarcas(){
    
    this.element = [
      {idPregunta: 1, descripcionPregunta: 'Hydrogen', tipo: 'liso',},
      {idPregunta: 2, descripcionPregunta: 'Helium', tipo: 'liso', },
      {idPregunta: 3, descripcionPregunta: 'Lithium', tipo: 'verde', },
      {idPregunta: 4, descripcionPregunta: 'Beryllium', tipo: 'liso', },
      {idPregunta: 5, descripcionPregunta: 'Boron', tipo: 'negro', },
      {idPregunta: 6, descripcionPregunta: 'Carbon', tipo: 'blanco',},
      {idPregunta: 7, descripcionPregunta: 'Nitrogen', tipo: 'azul', },
      {idPregunta: 8, descripcionPregunta: 'Oxygen', tipo: 'naranja', },
      {idPregunta: 9, descripcionPregunta: 'Fluorine', tipo: 'amarillo', },
      {idPregunta: 10, descripcionPregunta: 'Neon', tipo: 'naranja', },
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deletePregunta(idPregunta){
    console.log(idPregunta)
  }

  updatePregunta(idPregunta){
    console.log(idPregunta)
  }

  addPregunta(){
    console.log("Add Pregunta");
  }

  signOut(){
    localStorage.removeItem('adminLogged');
    this.router.navigate([''], { replaceUrl: true });
  }

}