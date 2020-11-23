import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  zones: any;
  constructor() { }


  ngOnInit(): void {
    this.getUbication();
  }

  getUbication(){

    this.zones = [
      { name: 'Los Caobos Av La Salle'},
      { name: 'Las Palmas Av Las Palmas' },
      { name: 'La Florida Av Andres Bello'},
    ]
  }

}
