import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.scss']
})

export class SampleComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idUsuario','nombreUsuario','apellidoUsuario','fecnacUsuario','icons'];
  constructor() { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMuestras();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getMuestras(){
    
    this.element = [
      {idUsuario: 1, nombreUsuario: 'Carlos23',apellidoUsuario:'Medicamentos',fecnacUsuario:'01/01/1999'},
      {idUsuario: 2, nombreUsuario: 'LOPZ1998',apellidoUsuario:'Frutas',fecnacUsuario:'01/01/1999'},
      {idUsuario: 3, nombreUsuario: 'PaoVar',apellidoUsuario:'Verduras',fecnacUsuario:'01/01/1999'},
      {idUsuario: 4, nombreUsuario: 'Ofic14',apellidoUsuario:'Pisos',fecnacUsuario:'01/01/1999'},
      {idUsuario: 5, nombreUsuario: 'Escolar75',apellidoUsuario:'Papeleria',fecnacUsuario:'01/01/1999'},
      {idUsuario: 6, nombreUsuario: 'Comr',apellidoUsuario:'Cuadernos',fecnacUsuario:'01/01/1999'},
      {idUsuario: 7, nombreUsuario: 'Mueb4',apellidoUsuario:'Vajillas',fecnacUsuario:'01/01/1999'},
      {idUsuario: 8, nombreUsuario: 'Computadoras185',apellidoUsuario:'Cubiertos',fecnacUsuario:'01/01/1999'},
      {idUsuario: 9, nombreUsuario: 'Hi99',apellidoUsuario:'Sofas',fecnacUsuario:'01/01/1999'},
      {idUsuario: 10, nombreUsuario: 'Masna74',apellidoUsuario:'Perifericos',fecnacUsuario:'01/01/1999'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteMuestra(idUsuario){
    console.log(idUsuario)
  }

  updateMuestra(idUsuario){
    console.log(idUsuario)
  }

  interviewMuestra(idUsuario){
    console.log(idUsuario)
  }

}