import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-category',
  templateUrl: './menu-category.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuCategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'nombreCategoria','icons'];
  constructor(private router: Router, private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getCategorias();
    
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
          console.log('entro')
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

  deleteCategoria(idCategoria){
    console.log(idCategoria)
  }

  updateCategoria(idCategoria){
    console.log(idCategoria)
  }

  addCategoria(){
    this.router.navigate(['/config/addCategory']);
    console.log("Add Categoria");
  }
}