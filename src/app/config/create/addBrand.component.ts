import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
@Component({
  selector: 'app-addbrand',
  templateUrl: './addbrand.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddBrandComponent implements OnInit{
    
  auxRes: any;
  createMarcaForm: FormGroup;
  admin: any;
  subCategorias:any;
  token: string;
  constructor(private formBuilder: FormBuilder,private adminService: AdminService) { }

  ngOnInit(): void {
    this.createMarcaForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      Tipo: ['', Validators.required],
      Capacidad: ['', Validators.required],
      Unidad: ['', Validators.required],
      selectSubCategoria: ['',Validators.required]
    });
    this.getSubCategoria();
  }

  handleCreateMarca(){
    let formData = new FormData();
    formData.append('Nombre', this.createMarcaForm.get('Nombre').value);
    console.log(this.createMarcaForm.get('selectSubCategoria').value)
  } 

  getSubCategoria(){
    this.subCategorias = [
      { idSubcategoria:1, name: 'Los Caobos Av La Salle'},
      { idSubcategoria:2, name: 'Las Palmas Av Las Palmas' },
      { idSubcategoria:3, name: 'La Florida Av Andres Bello'},
    ]
  }

}