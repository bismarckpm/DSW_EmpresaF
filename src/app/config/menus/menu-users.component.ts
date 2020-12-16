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
  element=new Array();
  elements:any
  dataSource:any;
  users: any;
  displayedColumns: string[] = ['idUsuario', 'nombre','nombreUsuario','rolUsuario','estadoUsuario','icons'];
  constructor(private router: Router, private adminService:AdminService,public _snackBar: MatSnackBar) { }
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
      this.getInfo();
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

  getInfo(){
    this.getRespondents();
    this.getClients();
  }

  getClients(){
    this.adminService.getClientes().
    subscribe(
      res => {
        let auxRes:any;
        auxRes = res;        
        if(auxRes.estado == 'success'){
          for (let user of auxRes.usuarios){
            this.element.push({idUsuario:user.id,nombre:user.nombre,nombreUsuario:user.nombreUsuario,rolUsuario:'cliente',estadoUsuario:user.estado});
          }
        }
        this.dataSource = new MatTableDataSource(this.element);
        this.dataSource.paginator = this.paginator;
      },
      err=>{
        console.log(err)
      }
    )
  }

  getRespondents(){
    this.adminService.getEncuestados().
    subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          for (let user of auxRes.usuarios){
            this.element.push({idUsuario:user.id,nombre:user.primer_nombre,nombreUsuario:user.nombreUsuario,rolUsuario:'encuestado',estadoUsuario:user.estado});
          }
        }
        this.dataSource = new MatTableDataSource(this.element);
        this.dataSource.paginator = this.paginator;
      },
      err=>{
        console.log(err)

      }
    )
  }

  deleteUser(rolUsuario,estadoUsuario,idUsuario){
    if (rolUsuario=='cliente'){
      if (estadoUsuario=='activo'){
        this.adminService.inactiveClient(idUsuario).
        subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Usuario inactivado");
              window.location.reload();
            }
          },
          err => {
            console.log(err)
          }
        )
      }else{
        this.adminService.activeClient(idUsuario).
        subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Usuario activado");
              window.location.reload();
            }
          },
          err => {
            console.log(err)
          }
        )
      }
    }else if(rolUsuario=='encuestado'){
      if (estadoUsuario=='activo'){
        this.adminService.inactiveRespondent(idUsuario).
        subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Usuario inactivado");
              window.location.reload();
            }
          },
          err => {
            console.log(err)
          }
        )
      }else{
        this.adminService.activeRespondent(idUsuario).
        subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Usuario activado");
              window.location.reload();
            }
          },
          err => {
            console.log(err)
          }
        )
      }
    }
  }

  updateUser(idUsuario,rolUsuario){
    if (rolUsuario=='cliente'){
      this.router.navigate(['/config/updateClient/'+idUsuario]);
    } else if(rolUsuario=='encuestado'){
      this.router.navigate(['/config/updateRespondent/'+idUsuario]);
    }else {
      this.router.navigate(['/config/updateUser/'+idUsuario]);
    }
  }

  addUser(){
    this.router.navigate(['/config/addUser']);
    console.log("Add Usuario");
  }
}