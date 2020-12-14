import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})


export class ClientComponent implements OnInit{
  elements:any;
  dataSource:any;
  panelOpenState = false;
  displayedColumns: string[] = ['idEstudio', 'estatusEstudio', 'edad', 'fechaIniEstudio','genero','icons'];
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.getEstudios();
    
  }

  getEstudios(){
    
    this.elements = [
      {idEstudio: 1, estatusEstudio: 'Activo', edad: 'liso', fechaIniEstudio: 'H',genero:'Masculino'},
      {idEstudio: 2, estatusEstudio: 'Activo', edad: 'liso', fechaIniEstudio: 'He',genero:'Masculino'},
      {idEstudio: 3, estatusEstudio: 'Lithium', edad: 'verde', fechaIniEstudio: 'Li',genero:'Masculino'},
      {idEstudio: 4, estatusEstudio: 'Inactivo', edad: 'liso', fechaIniEstudio: 'Be',genero:'Masculino'},
      {idEstudio: 5, estatusEstudio: 'Inactivo', edad: 'negro', fechaIniEstudio: 'B',genero:'Masculino'},
      {idEstudio: 6, estatusEstudio: 'Activo', edad: 'blanco', fechaIniEstudio: 'C',genero:'Masculino'},
      {idEstudio: 7, estatusEstudio: 'Activo', edad: 'azul', fechaIniEstudio: 'N',genero:'Masculino'},
      {idEstudio: 8, estatusEstudio: 'Oxygen', edad: 'naranja', fechaIniEstudio: 'O',genero:'Masculino'},
      {idEstudio: 9, estatusEstudio: 'Activo', edad: 'amarillo', fechaIniEstudio: 'F',genero:'Masculino'},
      {idEstudio: 10, estatusEstudio: 'Inactivo', edad: 'naranja', fechaIniEstudio: 'Ne',genero:'Masculino'},
    ];
  

  }

  updateEstudio(idEstudio){
    console.log(idEstudio)
  }

  solicitarEstudio(){
    this.router.navigate(['/pages/request-study']);
  }
}
