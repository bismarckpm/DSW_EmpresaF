import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-brand',
  templateUrl: './menu-brand.component.html',
  styleUrls: ['./menu.component.scss']
})


export class MenuBrandComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idMarca', 'nombreMarca', 'tipoMarca', 'capacidad','unidad','nombreSubcategoria','icons'];
  constructor(private router: Router) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getMarcas();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getMarcas(){
    
    this.element = [
      {idMarca: 1, nombreMarca: 'Hydrogen', tipoMarca: 'liso', capacidad: 'H',unidad: 2,nombreSubcategoria:'subca1'},
      {idMarca: 2, nombreMarca: 'Helium', tipoMarca: 'liso', capacidad: 'He',unidad: 2,nombreSubcategoria:'subca2'},
      {idMarca: 3, nombreMarca: 'Lithium', tipoMarca: 'verde', capacidad: 'Li',unidad: 2,nombreSubcategoria:'subca2'},
      {idMarca: 4, nombreMarca: 'Beryllium', tipoMarca: 'liso', capacidad: 'Be',unidad: 2,nombreSubcategoria:'subca3'},
      {idMarca: 5, nombreMarca: 'Boron', tipoMarca: 'negro', capacidad: 'B',unidad: 2,nombreSubcategoria:'subca4'},
      {idMarca: 6, nombreMarca: 'Carbon', tipoMarca: 'blanco', capacidad: 'C',unidad: 2,nombreSubcategoria:'subca5'},
      {idMarca: 7, nombreMarca: 'Nitrogen', tipoMarca: 'azul', capacidad: 'N',unidad: 2,nombreSubcategoria:'subca6'},
      {idMarca: 8, nombreMarca: 'Oxygen', tipoMarca: 'naranja', capacidad: 'O',unidad: 2,nombreSubcategoria:'subca6'},
      {idMarca: 9, nombreMarca: 'Fluorine', tipoMarca: 'amarillo', capacidad: 'F',unidad: 2,nombreSubcategoria:'subca5'},
      {idMarca: 10, nombreMarca: 'Neon', tipoMarca: 'naranja', capacidad: 'Ne',unidad: 2,nombreSubcategoria:'subca1'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteMarca(idMarca){
    console.log(idMarca)
  }

  updateMarca(idMarca){
    this.router.navigate(['/config/updateBrand']);
    console.log(idMarca)
  }

  addMarca(){
    this.router.navigate(['/config/addBrand']);
    console.log("Add marca");
  }
}
