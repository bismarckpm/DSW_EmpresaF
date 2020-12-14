import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-brand',
  templateUrl: './menu-brand.component.html',
  styleUrls: ['./menu.component.scss']
})


export class MenuBrandComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idMarca', 'nombreMarca', 'tipoMarca', 'capacidad','unidad','subcategoriaId','icons'];
  constructor(private router: Router, private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMarcas();
    
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getMarcas(){
    this.adminService.getMarcas()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          console.log(auxRes)
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

  deleteMarca(idMarca){
    console.log(idMarca)
  }

  updateMarca(idMarca){
    console.log(idMarca)
  }

  addMarca(){
    this.router.navigate(['/config/addBrand']);
    console.log("Add marca");
  }
}
