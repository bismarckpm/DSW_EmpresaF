import { Component, OnInit } from '@angular/core';
import { GlobalMenuMethodsService } from 'src/app/core/services/global-menu-methods.service';

@Component({
  selector: 'app-global-menuP',
  templateUrl: './global-menuP.component.html',
  styleUrls: ['./global-menuP.component.scss']
})
export class GlobalMenuPComponent implements OnInit {

  constructor( private globalMenuService: GlobalMenuMethodsService) { }

  ngOnInit(): void {
  }

  signOut(){
    this.globalMenuService.signOut();
  }

}
