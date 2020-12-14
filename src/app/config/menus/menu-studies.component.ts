import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-studies',
  templateUrl: './menu-studies.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuStudiesComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idEstudio','estadoEstudio','nombreCliente','nombreSubcategoria','fecIniEstudio','fecFinEstudio','icons'];
  constructor(private router: Router) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getStudies();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getStudies(){
    
    this.element = [
      {idEstudio: 1,estadoEstudio:'activo', nombreCliente: 'Carlos23',nombreSubcategoria:'Medicamentos',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 2,estadoEstudio:'activo', nombreCliente: 'LOPZ1998',nombreSubcategoria:'Frutas',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 3,estadoEstudio:'activo', nombreCliente: 'PaoVar',nombreSubcategoria:'Verduras',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 4,estadoEstudio:'inactivo', nombreCliente: 'Ofic14',nombreSubcategoria:'Pisos',fecIniEstudio:'01/01/2020',fecFinEstudio:'01/05/2020'},
      {idEstudio: 5,estadoEstudio:'activo', nombreCliente: 'Escolar75',nombreSubcategoria:'Papeleria',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 6,estadoEstudio:'inactivo', nombreCliente: 'Comr',nombreSubcategoria:'Cuadernos',fecIniEstudio:'01/01/2020',fecFinEstudio:'01/05/2020'},
      {idEstudio: 7,estadoEstudio:'activo', nombreCliente: 'Mueb4',nombreSubcategoria:'Vajillas',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 8,estadoEstudio:'inactivo', nombreCliente: 'Computadoras185',nombreSubcategoria:'Cubiertos',fecIniEstudio:'01/01/2020',fecFinEstudio:'01/05/2020'},
      {idEstudio: 9,estadoEstudio:'activo', nombreCliente: 'Hi99',nombreSubcategoria:'Sofas',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
      {idEstudio: 10,estadoEstudio:'activo', nombreCliente: 'Masna74',nombreSubcategoria:'Perifericos',fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteStudy(idEstudio){
    console.log(idEstudio)
  }

  updateStudy(idEstudio){
    this.router.navigate(['/config/updateStudies']);
    console.log(idEstudio)
  }

  addStudy(){
    this.router.navigate(['/config/addStudy']);
    console.log("Add Estudio");
  }
}