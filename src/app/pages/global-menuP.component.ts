import { Component, OnInit } from '@angular/core';
import { GlobalMenuMethodsService } from 'src/app/core/services/global-menu-methods.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-global-menuP',
  templateUrl: './global-menuP.component.html',
  styleUrls: ['./global-menuP.component.scss']
})
export class GlobalMenuPComponent implements OnInit {

  constructor(private globalMenuService: GlobalMenuMethodsService,
              private router: Router,) { }

  ngOnInit(): void {
  }

  signOut(){
    this.globalMenuService.signOut();
  }

  goToSurvey(){
    this.router.navigate(['pages/survey']);
  }

}
