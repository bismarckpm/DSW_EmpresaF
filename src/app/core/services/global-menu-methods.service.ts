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
    else if(localStorage.getItem('analistLogged')){
      localStorage.removeItem('analistLogged');
      this.router.navigate([''], { replaceUrl: true });
    }
    //Quitar despues
    this.router.navigate([''], { replaceUrl: true });
  }

}
