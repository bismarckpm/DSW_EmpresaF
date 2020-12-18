import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from './../../core/services/admin.service';

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
  constructor(private formBuilder: FormBuilder,private router: Router,public _snackBar: MatSnackBar,private adminService:AdminService) {

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
    let userStorage = localStorage.getItem('analistaLogged');
    this.iduser = JSON.parse(userStorage);
    this.iduser = this.iduser.id;
    this.adminService.getAdministrador(this.iduser).
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
      console.log(this.passwordForm.value);
      this.contrasena = this.passwordForm.get('contrasena').value;
      this.adminService.updateAdministrador(this.nombre,this.contrasena,this.iduser)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/analitics/profile']);
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
    } else {
      this.openSnackBar("Debe llenar todos los campos.");
    }
  }


  }
