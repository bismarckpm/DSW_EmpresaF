import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-studies',
  templateUrl: './updateStudies.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateStudiesComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = {
        estadoEstudio:'activo', nombreCliente: 'Carlos23',nombreSubcategoria:'Medicamentos',
        fecIniEstudio:'01/01/2020',fecFinEstudio:'Sin fecha fin'
    }
      
    
  }

  updateStudie(){
      console.log('Update')
  }
}
