import { Component,OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-menu-users',
  templateUrl: './menu-users.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuUsersComponent implements OnInit{
  element:any;
  elements:any
  dataSource:any;
  users: any;
  displayedColumns: string[] = ['idUsuario', 'nombreUsuario','rolUsuario','estadoUsuario','icons'];
  constructor(private router: Router, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.getUsers();
    
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getUsers(){
    this.getRespondents();
    this.getClients();
  }

  getRespondents(){
    this.adminService.getEncuestados().
    subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          for (let user of auxRes.usuarios){
            this.element.set({idUsuario:user.id,nombreUsuario:user.nombreUsuario,rolUsuario:'encuestado',estadoUsuario:user.estado});
            console.log(user.id,user.nombreUsuario,'encuestado',user.estado);
          }
          console.log(this.element)
        }
      },
      err=>{
        console.log(err)

      }
    )
  }

  getClients(){
    
  }

  deleteUser(idUsuario){
    console.log(idUsuario)
  }

  updateUser(idUsuario,rolUsuario){
    if (rolUsuario=='cliente'){
      this.router.navigate(['/config/updateClient'],idUsuario);
    } else if(rolUsuario=='encuestado'){
      this.router.navigate(['/config/updateRespondent'],idUsuario);
    }else {
      this.router.navigate(['/config/updateUser'],idUsuario);
    }
  }

  addUser(){
    this.router.navigate(['/config/addUser']);
    console.log("Add Usuario");
  }
}