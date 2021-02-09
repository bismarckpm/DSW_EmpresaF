import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-question',
  templateUrl: './menu-question.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuCQuestionComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'descripcion', 'tipo','opcion'];
  opcion:any;
  constructor(private router: Router,private adminService: AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getQuestions();
    
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getQuestions(){
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.adminService.getQuestions(token)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.element = auxRes.data;
          this.dataSource = new MatTableDataSource(auxRes.data);
          this.dataSource.paginator = this.paginator;
        }
      }, 
      err => {
        console.log(err)
      }
    )
  }

  deleteQuestion(idPregunta){
    console.log(idPregunta)
  }

  updateQuestion(idPregunta){
    this.router.navigate(['/config/updateQuestion']);
    console.log(idPregunta)
  }

  addQuestion(){
    this.router.navigate(['/config/addQuestion']);
  }

}