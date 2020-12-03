import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-menu-analitics',
  templateUrl: './menu-analitics.component.html',
  styleUrls: ['./menu-analitics.component.scss']
})

export class MenuAnaliticsComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idEstudio','nombreCliente','nombreSubcategoria','fecIniEstudio','icons'];
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
      {idEstudio: 1, nombreCliente: 'Carlos23',nombreSubcategoria:'Medicamentos',fecIniEstudio:'01/01/2020'},
      {idEstudio: 2, nombreCliente: 'LOPZ1998',nombreSubcategoria:'Frutas',fecIniEstudio:'01/01/2020'},
      {idEstudio: 3, nombreCliente: 'PaoVar',nombreSubcategoria:'Verduras',fecIniEstudio:'01/01/2020'},
      {idEstudio: 4, nombreCliente: 'Ofic14',nombreSubcategoria:'Pisos',fecIniEstudio:'01/01/2020'},
      {idEstudio: 5, nombreCliente: 'Escolar75',nombreSubcategoria:'Papeleria',fecIniEstudio:'01/01/2020'},
      {idEstudio: 6, nombreCliente: 'Comr',nombreSubcategoria:'Cuadernos',fecIniEstudio:'01/01/2020'},
      {idEstudio: 7, nombreCliente: 'Mueb4',nombreSubcategoria:'Vajillas',fecIniEstudio:'01/01/2020'},
      {idEstudio: 8, nombreCliente: 'Computadoras185',nombreSubcategoria:'Cubiertos',fecIniEstudio:'01/01/2020'},
      {idEstudio: 9, nombreCliente: 'Hi99',nombreSubcategoria:'Sofas',fecIniEstudio:'01/01/2020'},
      {idEstudio: 10, nombreCliente: 'Masna74',nombreSubcategoria:'Perifericos',fecIniEstudio:'01/01/2020'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  generateResults(idEstudio){
    console.log(idEstudio)
  }

  getMuestra(idEstudio){
    console.log(idEstudio)
  }

}