import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-users',
  templateUrl: './menu-users.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuUsersComponent implements OnInit{
  element:any;
  dataSource:any;
  displayedColumns: string[] = ['idUsuario', 'nombreUsuario','rolUsuario','estadoUsuario','icons'];
  constructor(private router: Router) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getUsers();
    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getUsers(){
    
    this.element = [
      {idUsuario: 1, nombreUsuario: 'Carlos23',rolUsuario:'admin',estadoUsuario:'activo'},
      {idUsuario: 2, nombreUsuario: 'LOPZ1998',rolUsuario:'cliente',estadoUsuario:'activo'},
      {idUsuario: 3, nombreUsuario: 'PaoVar',rolUsuario:'encuestado',estadoUsuario:'activo'},
      {idUsuario: 4, nombreUsuario: 'Ofic14',rolUsuario:'analista',estadoUsuario:'activo'},
      {idUsuario: 5, nombreUsuario: 'Escolar75',rolUsuario:'encuestado',estadoUsuario:'activo'},
      {idUsuario: 6, nombreUsuario: 'Comr',rolUsuario:'admin',estadoUsuario:'inactivo'},
      {idUsuario: 7, nombreUsuario: 'Mueb4',rolUsuario:'cliente',estadoUsuario:'activo'},
      {idUsuario: 8, nombreUsuario: 'Computadoras185',rolUsuario:'encuestado',estadoUsuario:'activo'},
      {idUsuario: 9, nombreUsuario: 'Hi99',rolUsuario:'encuestado',estadoUsuario:'activo'},
      {idUsuario: 10, nombreUsuario: 'Masna74',rolUsuario:'encuestado',estadoUsuario:'inactivo'},
    ];
    this.dataSource = new MatTableDataSource(this.element);

  }

  deleteUser(idUsuario){
    console.log(idUsuario)
  }

  updateUser(idUsuario){
  this.router.navigate(['/config/updateUser']);
    console.log(idUsuario)
  }

  addUser(){
    this.router.navigate(['/config/addUser']);
    console.log("Add Usuario");
  }
}