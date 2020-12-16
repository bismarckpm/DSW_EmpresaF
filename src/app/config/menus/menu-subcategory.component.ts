import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-subcategory',
  templateUrl: './menu-subcategory.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuSubcategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idSubcategoria', 'nombreSubcategoria','nombreCategoria','estadoSubategoria','icons'];
  constructor(private router: Router, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getSubcategorias();
    
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

  getSubcategorias(){
    this.adminService.getSubcategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.dataSource = new MatTableDataSource(auxRes.subcategorias);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  deleteSubcategory(idSubcategoria,estadoSubategoria){
    if (estadoSubategoria=='activo'){
      this.adminService.inactiveSubcategory(idSubcategoria).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Usuario inactivado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }else{
      this.adminService.activeSubcategory(idSubcategoria).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Usuario activado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }
  }

  updateSubcategory(idSubcategoria){
    this.router.navigate(['/config/updateSubcategory/'+idSubcategoria]);
  }

  addSubcategory(){
    this.router.navigate(['/config/addSubCategory']);
  }
}