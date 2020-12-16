import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

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
  nombreMarca:string
  tipoMarca:string
  capacidad:number
  unidad:string;

  constructor(private router: Router, private formBuilder: FormBuilder,private adminService: AdminService,public _snackBar: MatSnackBar) { }

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
  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleCreateMarca(){
    let idSubcategoria:number;
    this.nombreMarca =  this.createMarcaForm.get('Nombre').value;
    this.tipoMarca = this.createMarcaForm.get('Tipo').value;
    this.capacidad = this.createMarcaForm.get('Capacidad').value;
    this.unidad = this.createMarcaForm.get('Unidad').value;
    idSubcategoria = this.createMarcaForm.get('selectSubCategoria').value;
    this.adminService.createMarca(this.nombreMarca,this.tipoMarca,this.capacidad,this.unidad,idSubcategoria)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Marca creada con exito");
          this.router.navigate(['/config/menubrand']);
        }
      },
      err => {

      }
    )
  } 

  getSubCategoria(){
    this.adminService.getSubcategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.subCategorias = auxRes.subcategorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

}