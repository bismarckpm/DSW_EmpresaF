import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddUserComponent implements OnInit{
    registerUserForm:FormGroup;
    registerClienteForm:FormGroup;
    nombre:any;
    nombreUsuario:any;
    contrasena:any;
    roles:any;
    registro_cliente:boolean = true;

    constructor(private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }

    ngOnInit(): void {
      this.registerClienteForm = this.formBuilder.group({
        nombre:['', Validators.required],
        nombreUsuario: ['',Validators.required],
        contrasena: ['',Validators.required],
      })
      this.registerUserForm = this.formBuilder.group({
        nombreUsuario: ['',Validators.required],
        contrasena: ['',Validators.required],
        rol:['',Validators.required],
      })
      this.getRoles();
    }

    getRoles(){
      this.roles = [
        {idRol:1, nombre: 'Administrador'},
        {idRol:2, nombre: 'Analista' },
      ]
    }

    openSnackBar(message: string){
      this._snackBar.open(message, 'X', {
        duration: 3000,
      });
    }

    handleRegisterCliente(){
      this.nombre = this.registerClienteForm.get('nombre').value;
      this.nombreUsuario = this.registerClienteForm.get('nombreUsuario').value
      this.contrasena = this.registerClienteForm.get('contrasena').value
      this.adminService.registerCliente(this.nombre,this.nombreUsuario,this.contrasena)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Registro exitoso");
          }
          else if(auxRes.estado != 'success'){
            console.log(auxRes)
            this.openSnackBar("Registro fallido");
          }
        },
        err => {
          console.log(err)
        }
      )
    }

    handleRegisterUsuario(){
      this.nombreUsuario = this.registerClienteForm.get('nombreUsuario').value
      this.contrasena = this.registerClienteForm.get('contrasena').value
      /*this.adminService.registerCliente(this.nombreUsuario,this.contrasena)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Registro exitoso");
          }
          else if(auxRes.estado != 'success'){
            console.log(auxRes)
            this.openSnackBar("Registro fallido");
          }
        },
        err => {
          console.log(err)
        }
      )*/
    }
  
}