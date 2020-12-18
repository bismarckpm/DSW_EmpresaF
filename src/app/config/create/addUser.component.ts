import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';


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
    rol:any;
    roles:any;
    registro_cliente:boolean = true;

    constructor(private router: Router,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }

    ngOnInit(): void {
      this.registerClienteForm = this.formBuilder.group({
        nombre:['', Validators.required],
        nombreCliente: ['',Validators.required],
        contrasenaC: ['',Validators.required],
      })
      this.registerUserForm = this.formBuilder.group({
        nombreUsuario: ['',Validators.required],
        contrasenaU: ['',Validators.required],
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
      this.nombreUsuario = this.registerClienteForm.get('nombreCliente').value
      this.contrasena = this.registerClienteForm.get('contrasenaC').value
      this.adminService.registerCliente(this.nombre,this.nombreUsuario,this.contrasena)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Registro exitoso");
            this.router.navigate(['/config/menuusers']);
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
      this.nombreUsuario = this.registerUserForm.get('nombreUsuario').value
      this.contrasena = this.registerUserForm.get('contrasenaU').value
      this.rol = this.registerUserForm.get('rol').value
      console.log(this.nombreUsuario,this.contrasena,this.rol)
      if (this.rol=='Administrador'){
        this.adminService.registerAdministrador(this.nombreUsuario,this.contrasena)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Registro exitoso");
            this.router.navigate(['/config/menuusers']);
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
      }else if (this.rol=='Analista'){
      this.adminService.registerAnalista(this.nombreUsuario,this.contrasena)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Registro exitoso");
            this.router.navigate(['/config/menuusers']);
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
    }
  
}