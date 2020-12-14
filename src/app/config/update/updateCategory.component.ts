import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-category',
  templateUrl: './updateCategory.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateCategoryComponent implements OnInit {
    info: any;
  constructor() { }


  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(){

    this.info = 
        { nombreCategoria: 'Salud'}
    
    
  }

  updateCategory(){
      console.log('Update')
  }
}
