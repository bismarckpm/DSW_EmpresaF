import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/core/services/admin.service';

@Component({
  selector: 'app-menu-subcategory',
  templateUrl: './menu-subcategory.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuSubcategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idSubcategoria', 'nombreSubcategoria','nombreCategoria','icons'];
  constructor(private router: Router,private adminService:AdminService) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getSubcategorias();
    
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
        if(auxRes.estado == 'success'){
          auxRes = res;
          this.dataSource = new MatTableDataSource(auxRes.subcategorias);
          this.dataSource.paginator = this.paginator;
        }
      },
      err => {
        console.log(err)
      }
    )

    this.element = [
      {idSubcategoria: 1,nombreSubcategoria: 'Medicamentos',nombreCategoria: 'Salud'},
      {idSubcategoria: 2,nombreSubcategoria: 'Frutas',nombreCategoria: 'Comidas'},
      {idSubcategoria: 3,nombreSubcategoria: 'Verduras',nombreCategoria: 'Comidas'},
      {idSubcategoria: 4,nombreSubcategoria: 'Pisos',nombreCategoria: 'Aseo'},
      {idSubcategoria: 5,nombreSubcategoria: 'Papeleria',nombreCategoria: 'Oficina'},
      {idSubcategoria: 6,nombreSubcategoria: 'Cuadernos',nombreCategoria: 'Escolar'},
      {idSubcategoria: 7,nombreSubcategoria: 'Vajillas',nombreCategoria: 'Cocina'},
      {idSubcategoria: 8,nombreSubcategoria: 'Cubiertos',nombreCategoria: 'Cocina'},
      {idSubcategoria: 9,nombreSubcategoria: 'Sofas',nombreCategoria: 'Muebles'},
      {idSubcategoria: 10,nombreSubcategoria: 'Perifericos',nombreCategoria: 'Computadoras'},
      {idSubcategoria: 11,nombreSubcategoria: 'Desodorantes',nombreCategoria: 'Higiene'},
      {idSubcategoria: 12,nombreSubcategoria: 'Alimentos',nombreCategoria: 'Mascotas'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteSubcategory(idSubcategoria){
    console.log(idSubcategoria)
  }

  updateSubcategory(idSubcategoria){
    this.router.navigate(['/config/updateSubcategory']);
    console.log(idSubcategoria)
  }

  addSubcategory(){
    this.router.navigate(['/config/addSubCategory']);
  }
}