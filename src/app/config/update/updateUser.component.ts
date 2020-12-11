import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-user',
  templateUrl: './updateUser.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateUserComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = {  
        nombreUsuario: 'Carlos23',rolUsuario:'admin',estadoUsuario:'activo'
    }
    
  }

  updateUser(){
      console.log('Update')
  }
}
