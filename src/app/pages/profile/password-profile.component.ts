import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsersService } from './../../core/services/users.service';

@Component({
  selector: 'app-password-profile',
  templateUrl: './password-profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class PasswordProfileComponent implements OnInit {
  passwordForm: FormGroup;
  contrasena:any;
  iduser:any;
  nombre:any;
  token:any;
  constructor(private formBuilder: FormBuilder,private router: Router,public _snackBar: MatSnackBar,private userService:UsersService) {

      this.passwordForm = this.formBuilder.group({
        contrasena: ['', Validators.required]
      });

  }

  ngOnInit(): void {
    this.getUser();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getUser(){  
    let userStorage = localStorage.getItem('clientLogged');
    this.iduser = JSON.parse(userStorage);
    this.token = this.iduser.token;
    this.iduser = this.iduser.id;
    this.userService.getAdministrador(this.iduser,this.token).
    subscribe(
      res =>{
        let auxRes:any = res;
        this.nombre = auxRes.nombreUsuario;
      },
      err =>{
        console.log(err)
      }
    )
  }

  saveData(){
    if (this.passwordForm.valid) {
      this.contrasena = this.passwordForm.get('contrasena').value;
      this.userService.updateAdministrador(this.nombre,this.contrasena,this.iduser,this.token)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/pages/profile']);
            }
            else if(auxRes.estado != 'success'){
              this.openSnackBar("Actualización fallida");
            }
          },
          err => {
            console.log(err)
          }
        )
    } else {
      this.openSnackBar("Debe llenar todos los campos.");
    }
  }


  }
