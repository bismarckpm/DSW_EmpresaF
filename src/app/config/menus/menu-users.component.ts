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
    this.getAdminis();
    this.getAnalitics();
  }

  getClients(){
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.adminService.getClientes(token).
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
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.adminService.getEncuestados(token).
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

  getAdminis(){
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.adminService.getAdministradores(token).
    subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          for (let user of auxRes.usuarios){
            this.element.push({idUsuario:user.id,nombre:'Sin nombre en el sistema',nombreUsuario:user.nombreUsuario,rolUsuario:'administrador',estadoUsuario:user.estado});
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

  getAnalitics(){
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.adminService.getAnalistas(token).
    subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          for (let user of auxRes.usuarios){
            this.element.push({idUsuario:user.id,nombre:'Sin nombre en el sistema',nombreUsuario:user.nombreUsuario,rolUsuario:'analista',estadoUsuario:user.estado});
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
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    if (rolUsuario=='cliente'){
      if (estadoUsuario=='activo'){
        this.adminService.inactiveClient(idUsuario,token).
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
        this.adminService.activeClient(idUsuario,token).
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
        this.adminService.inactiveRespondent(idUsuario,token).
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
        this.adminService.activeRespondent(idUsuario,token).
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
    }else if(rolUsuario=='administrador'){
      if (estadoUsuario=='activo'){
        this.adminService.inactiveAdministrador(idUsuario,token).
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
        this.adminService.activeAdministrador(idUsuario,token).
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
    }else if (rolUsuario=='analista'){
      if (estadoUsuario=='activo'){
        this.adminService.inactiveAnalista(idUsuario,token).
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
        this.adminService.activeAnalista(idUsuario,token).
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
      this.router.navigate(['/config/updateUser/'+idUsuario+'/'+rolUsuario]);
    }
  }

  addUser(){
    this.router.navigate(['/config/addUser']);
    console.log("Add Usuario");
  }
}