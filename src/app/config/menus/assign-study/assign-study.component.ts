import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router,ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service'
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-assign-study',
  templateUrl: './assign-study.component.html',
  styleUrls: ['../menu.component.scss']
})
export class AssignStudyComponent implements OnInit {
  element:any;
  dataSource:any;
  sub: any;
  id: number;
  displayedColumns: string[] = ['id','nombreEstudio','estado','icons'];
  constructor(private router: Router, private adminService:AdminService,private route: ActivatedRoute,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getStudies();
    
  }
  
  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getStudies(){  
    this.adminService.getStudies()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
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

  assignStudy(idEstudio:number){
      this.sub = this.route.params.subscribe(params => {
      this.id = +params['id']; // (+) converts string 'id' to a number
      this.adminService.assignStudy(this.id,idEstudio)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Estudio asignado con exito");
          }
          else{
            this.openSnackBar("Ocurrio un problema");
          }
        },
        err => {
          console.log(err)
        }
      )
    });
  }
}
