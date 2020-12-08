import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addsubcategory',
  templateUrl: './addsubcategory.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddSubcategoryComponent implements OnInit{
    
  auxRes: any;
  createSubCategoriaForm: FormGroup;
  admin: any;
  categorias:any;
  fkCategoriaForm:any;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createSubCategoriaForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      selectCategoria: ['',Validators.required]
    });
    this.getCategoria();
  }

  handleCreateSubCategoria(){
    let formData = new FormData();
    formData.append('Nombre', this.createSubCategoriaForm.get('Nombre').value);
    formData.append('selectCategoria', this.createSubCategoriaForm.get('selectCategoria').value);
    //console.log(this.createSubCategoriaForm.get('selectCategoria').value)
  } 

  getCategoria(){
    this.categorias = [
      { name: 'Los Caobos Av La Salle'},
      { name: 'Las Palmas Av Las Palmas' },
      { name: 'La Florida Av Andres Bello'},
    ]
  }
  
}