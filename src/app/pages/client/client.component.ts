import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})


export class ClientComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idEstudio', 'estatusEstudio', 'nombreMarca', 'fechaIniEstudio','icons'];
  constructor() { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEstudios();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getEstudios(){
    
    this.element = [
      {idEstudio: 1, estatusEstudio: 'Hydrogen', nombreMarca: 'liso', fechaIniEstudio: 'H'},
      {idEstudio: 2, estatusEstudio: 'Helium', nombreMarca: 'liso', fechaIniEstudio: 'He'},
      {idEstudio: 3, estatusEstudio: 'Lithium', nombreMarca: 'verde', fechaIniEstudio: 'Li'},
      {idEstudio: 4, estatusEstudio: 'Beryllium', nombreMarca: 'liso', fechaIniEstudio: 'Be'},
      {idEstudio: 5, estatusEstudio: 'Boron', nombreMarca: 'negro', fechaIniEstudio: 'B'},
      {idEstudio: 6, estatusEstudio: 'Carbon', nombreMarca: 'blanco', fechaIniEstudio: 'C'},
      {idEstudio: 7, estatusEstudio: 'Nitrogen', nombreMarca: 'azul', fechaIniEstudio: 'N'},
      {idEstudio: 8, estatusEstudio: 'Oxygen', nombreMarca: 'naranja', fechaIniEstudio: 'O'},
      {idEstudio: 9, estatusEstudio: 'Fluorine', nombreMarca: 'amarillo', fechaIniEstudio: 'F'},
      {idEstudio: 10, estatusEstudio: 'Neon', nombreMarca: 'naranja', fechaIniEstudio: 'Ne'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  updateEstudio(idEstudio){
    console.log(idEstudio)
  }

  addEstudio(){
    console.log("Add estudio");
  }
}
