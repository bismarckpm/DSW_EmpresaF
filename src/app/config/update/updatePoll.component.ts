import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';
import { AdminService } from './../../core/services/admin.service';



@Component({
  selector: 'app-update-poll',
  templateUrl: './updatePoll.component.html',
  styleUrls: ['./update.component.scss']
})


export class UpdatePollComponent implements OnInit{
  element:any;
  dataSource:any;
  sub: any;
  id: number;
  x:number;
  displayedColumns: string[] = ['idPregunta', 'descripcionPregunta','tipo','icons'];
  constructor(private router: Router,private route: ActivatedRoute, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getQuestions();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getQuestions(){
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      let adminStorage = localStorage.getItem('administrador');
      let admin = JSON.parse(adminStorage);
      let token = admin.token;
      this.adminService.getQuestionP(this.id,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){   
            if (auxRes.data.length!=0){
              this.x = 1;
            }else{
              this.x = 0;
            }
            this.element = [auxRes.data];
            this.dataSource = new MatTableDataSource(auxRes.data);
            this.dataSource.paginator = this.paginator;
          }
        },
        err => {
          console.log(err)
        }
      )
    });
  }

  deleteQuestion(idPregunta){
    console.log(idPregunta)
  }

  updateQuestion(idPregunta){
    console.log(idPregunta)
  }

  addQuestion(){
    this.router.navigate(['/config/pollquestion/'+this.id+'/'+this.x]);
  }
}