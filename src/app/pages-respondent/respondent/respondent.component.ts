import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
    selector: 'app-respondent',
    templateUrl: './respondent.component.html',
    styleUrls: ['./respondent.component.scss']
  })

  export class RespondentComponent implements OnInit{
    element:any;
    dataSource:any;
    displayedColumns: string[] = ['estudioId','nombreEstudio','icons'];
    constructor(private router: Router,private userService:UsersService) { }
    
    @ViewChild(MatPaginator) paginator: MatPaginator;
  
    ngOnInit(): void {
      this.getEstudios();
      
    }
  
    applyFilter(event: Event) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

    getEstudios(){
      let encuestadoStorage = localStorage.getItem('encuestadoLogged');
      let encuestado = JSON.parse(encuestadoStorage);
      let token = encuestado.token;
      encuestado = encuestado.id;
      this.userService.getEstudioEncuestado(encuestado,token) 
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'sucess'){
              this.element = auxRes.estudios;
              this.dataSource = new MatTableDataSource(auxRes.estudios);
              this.dataSource.paginator = this.paginator;
          }
        },
        err => {
          console.log(err)
        }
      )
  
    }
  
    verEncuesta(idEncuesta){
      this.router.navigate(['pages-respondent/questions', idEncuesta]);
      console.log(idEncuesta)
    }
  
  }