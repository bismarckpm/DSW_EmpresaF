import { Component, OnInit } from '@angular/core';
import { GlobalMenuMethodsService } from 'src/app/core/services/global-menu-methods.service';

@Component({
  selector: 'app-global-menuA',
  templateUrl: './global-menuA.component.html',
  styleUrls: ['./global-menuA.component.scss']
})
export class GlobalMenuAComponent implements OnInit {

  constructor( private globalMenuService: GlobalMenuMethodsService) { }

  ngOnInit(): void {
  }

  signOut(){
    this.globalMenuService.signOut();
  }

}
