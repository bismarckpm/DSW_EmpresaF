import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService extends ApiService{

  validateUserCredentials(nombreUsuario:string,contrasena:string){
    return this.http.post(this.API_URL+'api/auth/login',{ "nombreUsuario": nombreUsuario, "contrasena": contrasena },this.httpOptions);
  }

  restartUserPassword(nombreUsuario:string){
    return this.http.post(this.API_URL+'api/recuperacion/recuperarcontrasena',{ "nombreUsuario": nombreUsuario},this.httpOptions);
  }
}
