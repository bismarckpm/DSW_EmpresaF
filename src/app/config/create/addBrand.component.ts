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
  nombreMarca : any;
  tipoMarca : any;
  capacidad : any;
  unidad : any;
  subcategoria : any;
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
    this.nombreMarca = this.createMarcaForm.get('Nombre').value;
    this.tipoMarca = this.createMarcaForm.get('Tipo').value;
    this.capacidad = this.createMarcaForm.get('Capacidad').value;
    this.unidad = this.createMarcaForm.get('Unidad').value;
    this.subcategoria = this.createMarcaForm.get('selectSubCategoria').value;
    this.adminService.registerBrand(this.nombreMarca,this.tipoMarca,this.capacidad,this.unidad,this.subcategoria)
    .subscribe(
      res => {
        console.log(res)
      },
      err => {
        console.log(err)
      }
    )
  } 

  getSubCategoria(){
    this.adminService.getSubcategorias()
    .subscribe(
      res => {
        let auxRes:any;
        if(auxRes.estado == 'success'){
          let auxRes:any;
          auxRes = res;
          this.subCategorias = auxRes.subCategorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }
}