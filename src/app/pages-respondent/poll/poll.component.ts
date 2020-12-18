import { Component, OnInit,ViewChild } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.scss']
})
export class PollComponent implements OnInit {

  element:any;
    dataSource:any;
    displayedColumns: string[] = ['idEncuesta','nombreSubcategoria','nombreMarca','fecIniEstudio','icons'];
    constructor(private router: Router,) { }
    
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
        {idEncuesta:'1',nombreSubcategoria:'Medicamentos',nombreMarca:'Sante',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'2',nombreSubcategoria:'Frutas',nombreMarca:'Fruta',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'3',nombreSubcategoria:'Verduras',nombreMarca:'Verdura',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'4',nombreSubcategoria:'Pisos',nombreMarca:'Click',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'5',nombreSubcategoria:'Papeleria',nombreMarca:'Ofimania',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'6',nombreSubcategoria:'Cuadernos',nombreMarca:'Norma',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'7',nombreSubcategoria:'Vajillas',nombreMarca:'Vidrio',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'8',nombreSubcategoria:'Cubiertos',nombreMarca:'Cubierto',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'9',nombreSubcategoria:'Sofas',nombreMarca:'Sofa',fecIniEstudio:'01/01/2020'},
        {idEncuesta:'10',nombreSubcategoria:'Perifericos',nombreMarca:'Panasonic',fecIniEstudio:'01/01/2020'},
      ];
      this.dataSource = new MatTableDataSource(this.element);
  
    }
  
    verPreguntas(idEncuesta){
      
      console.log(idEncuesta)
    }

}
