import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addbrand',
  templateUrl: './addbrand.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddBrandComponent implements OnInit{
    
  auxRes: any;
  createMarcaForm: FormGroup;
  admin: any;
  token: string;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createMarcaForm = this.formBuilder.group({
      Nombre: ['', Validators.required]
    });
  }

  handleCreateMarca(){
    let formData = new FormData();
    formData.append('Nombre', this.createMarcaForm.get('Nombre').value);
    console.log(this.createMarcaForm.get('Nombre').value)
  } 
}