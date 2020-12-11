import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-brand',
  templateUrl: './updateBrand.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateBrandComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = {  
        nombreMarca: 'Hydrogen', tipoMarca: 'liso', capacidad: 'H',unidad: 2,nombreSubcategoria:'subca1'
    }
    
  }

  updateBrand(){
      console.log('Update')
  }
}
