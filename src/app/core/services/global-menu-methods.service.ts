import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GlobalMenuMethodsService {

  constructor(private router: Router) { }

  signOut(){
    if(localStorage.getItem('adminLogged')){
      localStorage.removeItem('adminLogged');
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
