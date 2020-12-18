import { Component, OnInit } from '@angular/core';
import { GlobalMenuMethodsService } from 'src/app/core/services/global-menu-methods.service';
@Component({
  selector: 'app-global-menu',
  templateUrl: './global-menu.component.html',
  styleUrls: ['./global-menu.component.scss']
})
export class GlobalMenuComponent implements OnInit {
  constructor( private globalMenuService: GlobalMenuMethodsService) { }

  ngOnInit(): void {
  }

  signOut(){
    this.globalMenuService.signOut();
  }

}
