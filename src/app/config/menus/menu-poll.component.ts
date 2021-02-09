import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-poll',
  templateUrl: './menu-poll.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuPollComponent implements OnInit{
  element:any;
  dataSource:any;
  x:number;
  displayedColumns: string[] = ['encuestaId', 'nombreEncuesta','subcategoria','preguntas','icons'];
  constructor(private router: Router,private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEncuestas();
    
  }
 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
  getQuestions(idEncuesta:number){
      let adminStorage = localStorage.getItem('administrador');
      let admin = JSON.parse(adminStorage);
      let token = admin.token;
      this.adminService.getQuestionP(idEncuesta,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){   
            if (auxRes.data.length!=0){
              this.x = 1;
              this.updateEncuesta(idEncuesta);
            }else{
              this.x = 0;
            }
          }
        },
        err => {
          console.log(err)
        }
      )
  }

  getEncuestas(){
    this.adminService.getEncuesta()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        console.log('entro')
        if(auxRes.estado == 'success'){
          this.element = auxRes.encuestas
          this.dataSource = new MatTableDataSource(auxRes.encuestas);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )

  }

  deleteEncuesta(idEncuesta){
    console.log(idEncuesta)
  }

  updateEncuesta(idEncuesta){
    this.router.navigate(['/config/pollquestion/'+idEncuesta+'/'+this.x]);
    console.log(idEncuesta)
  }

  addEncuesta(){
    this.router.navigate(['/config/addPoll']);
  }
}