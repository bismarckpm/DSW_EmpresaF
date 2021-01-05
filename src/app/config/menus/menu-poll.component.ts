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
  displayedColumns: string[] = ['encuestaId', 'nombreEncuesta','subcategoria','icons'];
  constructor(private router: Router,private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEncuestas();
    
  }
 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
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
    this.router.navigate(['/config/updatePoll/'+idEncuesta]);
    console.log(idEncuesta)
  }

  addEncuesta(){
    this.router.navigate(['/config/addPoll']);
  }
}