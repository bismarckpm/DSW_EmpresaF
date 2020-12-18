import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './updateUser.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateUserComponent implements OnInit {
  updateUsuarioForm: FormGroup;
  contrasena:any;
  categoria:any;
  sub: any;
  id: number;
  rol:any;
  oldnombre:any;
  val:number;
  constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.updateUsuarioForm = this.formBuilder.group({
      contrasena:['', Validators.required]
    })
    this.getInfo();
  }

  getInfo(){
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.rol = params['rol'];
      if (this.rol=='administrador'){
        this.adminService.getAdministrador(this.id).
        subscribe(
          res =>{
            let auxRes:any = res;
            this.oldnombre = auxRes.nombreUsuario;
          },
          err =>{
            console.log(err)
          }
        )
      }else if(this.rol=='analista'){
        this.adminService.getAnalista(this.id).
        subscribe(
          res =>{
            let auxRes:any = res;
            this.oldnombre = auxRes.nombreUsuario;
          },
          err =>{
            console.log(err)
          }
        )
      }
    });
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  updateAdmin(){
    this.adminService.updateAdministrador(this.oldnombre,this.contrasena,this.id)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/config/menuusers']);
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
  }

  updateAnalis(){
    this.adminService.updateAnalista(this.oldnombre,this.contrasena,this.id)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/config/menuusers']);
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
  }

  handleUpdateUsuario(){
    this.contrasena = this.updateUsuarioForm.get('contrasena').value;
    if (this.contrasena){
      this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        this.rol = params['rol'];
        if (this.rol=='administrador'){
          this.updateAdmin();
        }else if(this.rol=='analista'){
          this.updateAnalis();
        }
      });  
    }else{
      this.openSnackBar("Debe ingresar al menos un campo para realizar la modificación");
    }
  }
}
