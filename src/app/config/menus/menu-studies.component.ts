import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-studies',
  templateUrl: './menu-studies.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuStudiesComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id','nombreEstudio','estado','icons'];
  constructor(private router: Router,private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getStudies();
    
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
          this.element = auxRes.estudios
          this.dataSource = new MatTableDataSource(auxRes.estudios);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {

      }
    )
   

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