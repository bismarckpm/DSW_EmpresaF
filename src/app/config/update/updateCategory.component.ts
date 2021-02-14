import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-category',
  templateUrl: './updateCategory.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateCategoryComponent implements OnInit {
  updateCategoriaForm: FormGroup;
  nombre:any;
  sub: any;
  id: number;
  oldnombre:any;
  constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.updateCategoriaForm = this.formBuilder.group({
      nombre:['', Validators.required],
    })
    this.getInfo();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getInfo(){
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.adminService.getCategoria(this.id).
      subscribe(
        res =>{
          let auxRes:any = res;
          this.oldnombre = auxRes.nombreCategoria;
        },
        err =>{
          console.log(err)
        }
      )
    });
  }

  handleUpdateCategoria(){
    this.nombre = this.updateCategoriaForm.get('nombre').value;
    if (this.nombre){
      this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        let adminStorage = localStorage.getItem('administrador');
        let admin = JSON.parse(adminStorage);
        let token = admin.token; 
        this.adminService.updateCategoria(this.nombre,this.id,token)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/config/menucategory']);
            }
            else if(auxRes.estado != 'success'){
              console.log(auxRes)
              this.openSnackBar("Actualización fallida");
            }
          },
          err => {
            console.log(err)
          }
        )
      });  
    }else{
      this.openSnackBar("Debe ingresar al menos un campo para realizar la modificación");
    }
  }

}
