import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addpoll',
  templateUrl: './addpoll.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddPollComponent implements OnInit{
    
  auxRes: any;
  createEncuestaForm: FormGroup;
  admin: any;
  subCategorias:any;
  token: string;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createEncuestaForm = this.formBuilder.group({
      selectSubCategoria: ['',Validators.required]
    });
    this.getSubCategoria();
  }

  handleCreateMarca(){
    let formData = new FormData();
    formData.append('selectSubCategoria', this.createEncuestaForm.get('selectSubCategoria').value);
    console.log(this.createEncuestaForm.get('selectSubCategoria').value)
  } 

  getSubCategoria(){
    this.subCategorias = [
      { idSubcategoria:1, name: 'Los Caobos Av La Salle'},
      { idSubcategoria:2, name: 'Las Palmas Av Las Palmas' },
      { idSubcategoria:3, name: 'La Florida Av Andres Bello'},
    ]
  }
  
}