import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-menu-subcategory',
  templateUrl: './menu-subcategory.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuSubcategoryComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idSubcategoria', 'nombreSubcategoria','nombreCategoria','icons'];
  constructor() { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getSubcategorias();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getSubcategorias(){
    
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

  deleteSubcategoria(idSubcategoria){
    console.log(idSubcategoria)
  }

  updateSubcategoria(idSubcategoria){
    console.log(idSubcategoria)
  }

  addSubcategoria(){
    console.log("Add Subcategoria");
  }
}