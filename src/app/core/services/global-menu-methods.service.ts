import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AnalystService } from 'src/app/core/services/analyst.service';
import { AdminService } from 'src/app/core/services/admin.service';
import { UsersService } from 'src/app/core/services/users.service';
@Injectable({
  providedIn: 'root'
})
export class GlobalMenuMethodsService {

  constructor(private router: Router,private analystService:AnalystService,private adminService:AdminService,private usersService:UsersService) { }

  signOut(){
    if(localStorage.getItem('administrador')){
      let adminStorage = localStorage.getItem('administrador');
      let admin = JSON.parse(adminStorage);
      let token = admin.token;
      let idAdmin = admin.id;
      this.adminService.removeToken(idAdmin,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            localStorage.removeItem('administrador');
            this.router.navigate([''], { replaceUrl: true });
          }
        },
        err => {
          console.log(err)
        }
      )
    }
    else if(localStorage.getItem('clientLogged')){
      let clientStorage = localStorage.getItem('clientLogged');
      let client = JSON.parse(clientStorage);
      let token = client.token;
      let idClient = client.id;
      this.adminService.removeToken(idClient,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            localStorage.removeItem('clientLogged');
            this.router.navigate([''], { replaceUrl: true });
          }
        },
        err => {
          console.log(err)
        }
      )
    }
    else if(localStorage.getItem('analistaLogged')){
      let analistStorage = localStorage.getItem('analistaLogged');
      let analista = JSON.parse(analistStorage);
      let token = analista.token;
      let idAnalista = analista.id
      this.analystService.removeToken(idAnalista,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            localStorage.removeItem('analistaLogged');
            localStorage.removeItem('encuesta');
            this.router.navigate([''], { replaceUrl: true });
          }
        },
        err => {
          console.log(err)
        }
      )
    }
    else if(localStorage.getItem('encuestadoLogged')){
      let encuestadoStorage = localStorage.getItem('encuestadoLogged');
      let encuestado = JSON.parse(encuestadoStorage);
      let token = encuestado.token;
      let idEncuestado = encuestado.id;
      this.adminService.removeToken(idEncuestado,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            localStorage.removeItem('encuestadoLogged');
            this.router.navigate([''], { replaceUrl: true });
          }
        },
        err => {
          console.log(err)
        }
      )
    }
  }

}
