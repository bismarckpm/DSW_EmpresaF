import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-updateClient',
  templateUrl: './updateClient.component.html',
  styleUrls: ['./update.component.scss']
})

export class UpdateClientComponent implements OnInit {
    updateClienteForm: FormGroup;
    nombre:any;
    contrasena:any;
    idCliente:any=3;
    oldnombre:any
    constructor(private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.updateClienteForm = this.formBuilder.group({
        nombre:['', Validators.required],
        contrasena:null,
      })
    this.getClient(this.idCliente);
  }
  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getClient(id){
    this.adminService.getCliente(id).
    subscribe(
      res =>{
        let auxRes:any = res;
        this.oldnombre = auxRes.nombre;
      },
      err =>{
        console.log(err)
      }
    )
  }

  handleUpdateCliente(){
    this.nombre = this.updateClienteForm.get('nombre').value;
    this.contrasena = this.updateClienteForm.get('contrasena').value;
    if (!this.contrasena){
        this.contrasena = null;
    }
    this.adminService.updateCliente(this.nombre,this.contrasena,3)
    .subscribe(
      res => {
        let auxRes:any = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Actualización exitosa");
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
    console.log(this.nombre)
    console.log(this.contrasena)
  }
}