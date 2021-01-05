import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-brand',
  templateUrl: './menu-brand.component.html',
  styleUrls: ['./menu.component.scss']
})


export class MenuBrandComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idMarca', 'nombreMarca', 'tipoMarca', 'capacidad','unidad','subcategoriaId','estado','icons'];
  constructor(private router: Router, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMarcas();
    
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getMarcas(){
    this.adminService.getMarcas()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.element = auxRes.marcas;
          this.dataSource = new MatTableDataSource(auxRes.marcas);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  deleteMarca(idMarca,estadoMarca){
    if (estadoMarca=='activo'){
      this.adminService.inactiveBrand(idMarca).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Marca inactivado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }else{
      this.adminService.activeBrand(idMarca).
      subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Marca activado");
            window.location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }
  }

  updateMarca(idMarca){
    this.router.navigate(['/config/updateBrand/'+idMarca]);
  }

  addMarca(){
    this.router.navigate(['/config/addBrand']);
  }
}
