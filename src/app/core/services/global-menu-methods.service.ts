import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GlobalMenuMethodsService {

  constructor(private router: Router) { }

  signOut(){
    if(localStorage.getItem('administrador')){
      localStorage.removeItem('administrador');
      this.router.navigate([''], { replaceUrl: true });
    }
    else if(localStorage.getItem('clientLogged')){
      localStorage.removeItem('clientLogged');
      this.router.navigate([''], { replaceUrl: true });
    }
  }

}
