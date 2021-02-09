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
      localStorage.removeItem('administrador');
      this.router.navigate([''], { replaceUrl: true });
    }
    else if(localStorage.getItem('clientLogged')){
      localStorage.removeItem('clientLogged');
      this.router.navigate([''], { replaceUrl: true });
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
      localStorage.removeItem('encuestadoLogged');
      this.router.navigate([''], { replaceUrl: true });
    }
  }

}
