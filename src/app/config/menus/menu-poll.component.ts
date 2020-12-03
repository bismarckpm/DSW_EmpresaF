import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-menu-poll',
  templateUrl: './menu-poll.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuPollComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idEncuesta', 'nombreSubcategoria','icons'];
  constructor() { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEncuestas();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getEncuestas(){
    
    this.element = [
      {idEncuesta: 1, nombreSubcategoria: 'Salud'},
      {idEncuesta: 2, nombreSubcategoria: 'Comidas'},
      {idEncuesta: 3, nombreSubcategoria: 'Aseo'},
      {idEncuesta: 4, nombreSubcategoria: 'Oficina'},
      {idEncuesta: 5, nombreSubcategoria: 'Escolar'},
      {idEncuesta: 6, nombreSubcategoria: 'Cocina'},
      {idEncuesta: 7, nombreSubcategoria: 'Muebles'},
      {idEncuesta: 8, nombreSubcategoria: 'Computadoras'},
      {idEncuesta: 9, nombreSubcategoria: 'Higiene'},
      {idEncuesta: 10, nombreSubcategoria: 'Mascotas'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteEncuesta(idEncuesta){
    console.log(idEncuesta)
  }

  updateEncuesta(idEncuesta){
    console.log(idEncuesta)
  }

  addEncuesta(){
    console.log("Add Encuesta");
  }
}