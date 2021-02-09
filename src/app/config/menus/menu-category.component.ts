import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-category',
  templateUrl: './menu-category.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuCategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'nombreCategoria','estadoCategoria','icons'];
  constructor(private router: Router, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getCategorias();
    
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
  getCategorias(){
    this.adminService.getCategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.element = [auxRes.categorias];
          this.dataSource = new MatTableDataSource(auxRes.categorias);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )

  }

  deleteCategoria(idCategoria,estadoCategoria){
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token; 
    if (estadoCategoria=='activo'){
      this.adminService.inactiveCategory(idCategoria,token).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Categoría inactivado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }else{
      this.adminService.activeCategory(idCategoria,token).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Categoría activado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }
  }

  updateCategoria(idCategoria){
    this.router.navigate(['/config/updateCategory/'+idCategoria]);
  }

  addCategoria(){
    this.router.navigate(['/config/addCategory']);
  }
}