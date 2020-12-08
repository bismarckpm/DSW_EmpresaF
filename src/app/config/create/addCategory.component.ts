import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addcategory',
  templateUrl: './addcategory.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddCategoryComponent implements OnInit{
    
  auxRes: any;
  createCategoriaForm: FormGroup;
  admin: any;
  token: string;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createCategoriaForm = this.formBuilder.group({
      Nombre: ['', Validators.required]
    });
  }

  handleCreateCategoria(){
    let formData = new FormData();
    formData.append('Nombre', this.createCategoriaForm.get('Nombre').value);
    console.log(this.createCategoriaForm.get('Nombre').value)
  } 
}