import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-subcategory',
  templateUrl: './updateSubcategory.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateSubcategoryComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = {  
        nombreSubcategoria: 'Hydrogen', nombreCategoria: 'liso'
    }
    
  }

  updateSubcategory(){
      console.log('Update')
  }
}
