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
  panelOpenState = false;
  displayedColumns: string[] = ['idEstudio', 'estatusEstudio', 'edad', 'fechaIniEstudio','genero','icons'];
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
      {idEstudio: 1, estatusEstudio: 'Hydrogen', edad: 'liso', fechaIniEstudio: 'H',genero:'Masculino'},
      {idEstudio: 2, estatusEstudio: 'Helium', edad: 'liso', fechaIniEstudio: 'He',genero:'Masculino'},
      {idEstudio: 3, estatusEstudio: 'Lithium', edad: 'verde', fechaIniEstudio: 'Li',genero:'Masculino'},
      {idEstudio: 4, estatusEstudio: 'Beryllium', edad: 'liso', fechaIniEstudio: 'Be',genero:'Masculino'},
      {idEstudio: 5, estatusEstudio: 'Boron', edad: 'negro', fechaIniEstudio: 'B',genero:'Masculino'},
      {idEstudio: 6, estatusEstudio: 'Carbon', edad: 'blanco', fechaIniEstudio: 'C',genero:'Masculino'},
      {idEstudio: 7, estatusEstudio: 'Nitrogen', edad: 'azul', fechaIniEstudio: 'N',genero:'Masculino'},
      {idEstudio: 8, estatusEstudio: 'Oxygen', edad: 'naranja', fechaIniEstudio: 'O',genero:'Masculino'},
      {idEstudio: 9, estatusEstudio: 'Fluorine', edad: 'amarillo', fechaIniEstudio: 'F',genero:'Masculino'},
      {idEstudio: 10, estatusEstudio: 'Neon', edad: 'naranja', fechaIniEstudio: 'Ne',genero:'Masculino'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  updateEstudio(idEstudio){
    console.log(idEstudio)
  }

  solicitarEstudio(){
    console.log("Add estudio");
  }
}
