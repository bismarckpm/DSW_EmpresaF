import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalystGuard implements CanActivate {
  constructor(private router:Router,){
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
      if(localStorage.getItem('analistaLogged')){
        return true;
      }
      else if(!localStorage.getItem('analistaLogged')){
        return false;
      } 
  }
  
}
