import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
    selector: 'app-respondent',
    templateUrl: './respondent.component.html',
    styleUrls: ['./respondent.component.scss']
  })

  export class RespondentComponent implements OnInit{
    element:any;
    dataSource:any;
    displayedColumns: string[] = ['idEstudio','estado','nombreMarca','fecIniEstudio','icons'];
    constructor(private router: Router,) { }
    
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
        {idEstudio:'1',estado:'Medicamentos',nombreMarca:'Sante',fecIniEstudio:'01/01/2020'},
        {idEstudio:'2',estado:'Frutas',nombreMarca:'Fruta',fecIniEstudio:'01/01/2020'},
        {idEstudio:'3',estado:'Verduras',nombreMarca:'Verdura',fecIniEstudio:'01/01/2020'},
        {idEstudio:'4',estado:'Pisos',nombreMarca:'Click',fecIniEstudio:'01/01/2020'},
        {idEstudio:'5',estado:'Papeleria',nombreMarca:'Ofimania',fecIniEstudio:'01/01/2020'},
        {idEstudio:'6',estado:'Cuadernos',nombreMarca:'Norma',fecIniEstudio:'01/01/2020'},
        {idEstudio:'7',estado:'Vajillas',nombreMarca:'Vidrio',fecIniEstudio:'01/01/2020'},
        {idEstudio:'8',estado:'Cubiertos',nombreMarca:'Cubierto',fecIniEstudio:'01/01/2020'},
        {idEstudio:'9',estado:'Sofas',nombreMarca:'Sofa',fecIniEstudio:'01/01/2020'},
        {idEstudio:'10',estado:'Perifericos',nombreMarca:'Panasonic',fecIniEstudio:'01/01/2020'},
      ];
      this.dataSource = new MatTableDataSource(this.element);
  
    }
  
    verEncuesta(idEncuesta){
      this.router.navigate(['pages/poll', idEncuesta]);
      console.log(idEncuesta)
    }
  
  }