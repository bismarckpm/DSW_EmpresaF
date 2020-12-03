import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-menu-category',
  templateUrl: './menu-category.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuCategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idCategoria', 'nombreCategoria','icons'];
  constructor() { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getCategorias();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getCategorias(){
    
    this.element = [
      {idCategoria: 1, nombreCategoria: 'Salud'},
      {idCategoria: 2, nombreCategoria: 'Comidas'},
      {idCategoria: 3, nombreCategoria: 'Aseo'},
      {idCategoria: 4, nombreCategoria: 'Oficina'},
      {idCategoria: 5, nombreCategoria: 'Escolar'},
      {idCategoria: 6, nombreCategoria: 'Cocina'},
      {idCategoria: 7, nombreCategoria: 'Muebles'},
      {idCategoria: 8, nombreCategoria: 'Computadoras'},
      {idCategoria: 9, nombreCategoria: 'Higiene'},
      {idCategoria: 10, nombreCategoria: 'Mascotas'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteCategoria(idCategoria){
    console.log(idCategoria)
  }

  updateCategoria(idCategoria){
    console.log(idCategoria)
  }

  addCategoria(){
    console.log("Add Categoria");
  }
}