import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-sample',
  templateUrl: './update-sample.component.html',
  styleUrls: ['./update-sample.component.scss']
})
export class UpdateSampleComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = {   
        nombre: 'Pedro',
        apellido: 'Perez',
        estadocivil:'soltero',
        fechanac: '11/01/1999'
      }
    
  }

  updateUsuario(){
      console.log('Update')
  }
}
