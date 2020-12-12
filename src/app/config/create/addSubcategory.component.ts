import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-addsubcategory',
  templateUrl: './addsubcategory.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddSubcategoryComponent implements OnInit{
    
  createSubCategoriaForm: FormGroup;
  admin: any;
  categorias:any;
  nombreSubcategoria:string;
  idCategoria:string;
  constructor(private formBuilder: FormBuilder,private adminService:AdminService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createSubCategoriaForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      selectCategoria: ['',Validators.required]
    });
    this.getCategorias();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleCreateSubCategoria(){
    this.nombreSubcategoria = this.createSubCategoriaForm.get('Nombre').value;
    this.idCategoria = this.createSubCategoriaForm.get('selectCategoria').value;
    this.adminService.createSubCategoria(this.nombreSubcategoria,this.idCategoria)
    .subscribe(
      res => {
        let auxRes:any;
        if(res.estado == 'success'){
          auxRes = res;
          this.openSnackBar("Subcategoria creada con exito");
        }
        else if(res.estado != 'success'){
          this.openSnackBar(auxRes.mensaje);
        }
      }, 
      err => {
        console.log(err)
      }
    )
  } 

  getCategorias(){
    this.adminService.getCategorias()
    .subscribe(
      res => {
        if(res.estado == 'success'){
          let auxRes:any;
          auxRes = res;
          this.categorias = auxRes.categorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }
  
}