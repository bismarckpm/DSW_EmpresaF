import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addcategory',
  templateUrl: './addcategory.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddCategoryComponent implements OnInit{
    
  auxRes: any;
  createCategoriaForm: FormGroup;
  nombreCategoria: string;
  token: string;
  constructor(private router: Router,private formBuilder: FormBuilder,private adminService:AdminService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createCategoriaForm = this.formBuilder.group({
      Nombre: ['', Validators.required]
    });
    
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleCreateCategoria(){
    this.nombreCategoria = this.createCategoriaForm.get('Nombre').value;
    console.log(this.nombreCategoria)
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token; 
    this.adminService.createCategoria(this.nombreCategoria,token)
    .subscribe(
      res => {
        let auxRes:any = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Categoria creada con exito");
          this.router.navigate(['/config/menucategory']);
        }
        else if(auxRes.estado != 'success'){
          this.openSnackBar("Creacion fallida");
        }
      },
      err => {
        console.log(err)
      }
    )
  } 
}