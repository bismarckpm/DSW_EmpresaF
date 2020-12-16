import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from 'src/app/core/services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  generos:any;
  estadosCiviles:any;
  registerEncuestadoForm: FormGroup;

  //Campos del form
  nombre:any;
  numeroIdentificacion:any;
  primerNombre:any;
  primerApellido:any;
  direccionComplemento:any;
  fechaNacimiento:any;
  genero:any;
  estadoCivil:any;
  ocupacion:any;
  Parroquias:any;
  nivelEstudio:any;
  nivelSocioeconomico:any;
  telefono:any;
  nombreUsuario:any;
  contrasena:any;
  constructor(private formBuilder: FormBuilder, private userService:UsersService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.registerEncuestadoForm = this.formBuilder.group({
      numeroIdentificacion: ['', Validators.required],
      primerNombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      direccionComplemento: ['', Validators.required],
      fechaNacimiento: ['',Validators.required],
      genero: ['',Validators.required],
      estadoCivil: ['',Validators.required],
      ocupacion: ['',Validators.required],
      Selectparroquia: ['',Validators.required],
      nivelEstudio: ['',Validators.required],
      nivelSocioeconomico: ['',Validators.required],
      telefono: ['',Validators.required],
      nombreUsuario: ['',Validators.required],
      contrasena: ['',Validators.required],
    });
    this.getGenero();
    this.getEstadoCivil();
    this.getParroquia();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getGenero(){
    this.generos = [
      {idGenero:1, descripcion: 'Masculino'},
      {idGenero:2, descripcion: 'Femenino' },
    ]
  }

  getEstadoCivil(){
    this.estadosCiviles = [
      {idEstado:1,descripcion: 'soltero'},
      {idEstado:2 ,descripcion: 'casado' },
      {idEstado:3 ,descripcion: 'divorciado' }
    ]
  }

  getParroquia(){
    this.userService.getParroquias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.Parroquias = auxRes.parroquias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  handleRegisterEncuestado(){
    let idParroquia
    this.numeroIdentificacion = this.registerEncuestadoForm.get('numeroIdentificacion').value;
    this.primerNombre = this.registerEncuestadoForm.get('primerNombre').value
    this.primerApellido = this.registerEncuestadoForm.get('primerApellido').value;
    this.direccionComplemento = this.registerEncuestadoForm.get('direccionComplemento').value
    this.fechaNacimiento = this.registerEncuestadoForm.get('fechaNacimiento').value
    this.genero = this.registerEncuestadoForm.get('genero').value
    this.estadoCivil =   this.registerEncuestadoForm.get('estadoCivil').value
    this.ocupacion = this.registerEncuestadoForm.get('ocupacion').value
    idParroquia = this.registerEncuestadoForm.get('Selectparroquia').value
    this.nivelEstudio = this.registerEncuestadoForm.get('nivelEstudio').value
    this.nivelSocioeconomico = this.registerEncuestadoForm.get('nivelSocioeconomico').value
    this.telefono = this.registerEncuestadoForm.get('telefono').value
    let codigo = this.telefono.substring(0,4);
    let numero = this.telefono.substring(4,11)
    this.nombreUsuario = this.registerEncuestadoForm.get('nombreUsuario').value
    this.contrasena = this.registerEncuestadoForm.get('contrasena').value
    this.userService.registerEncuestado(
                                  this.numeroIdentificacion,
                                  this.primerNombre,
                                  this.primerApellido,
                                  this.direccionComplemento,
                                  this.fechaNacimiento,
                                  this.genero,
                                  this.estadoCivil,
                                  this.ocupacion,
                                  idParroquia,
                                  this.nivelEstudio,
                                  this.nivelSocioeconomico,
                                  codigo,
                                  numero,
                                  this.nombreUsuario,
                                  this.contrasena)
    .subscribe(
      res => {
        let auxRes:any = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Registro exitoso");
        }
        else if(auxRes.estado != 'success'){
          this.openSnackBar("Registro fallido");
        }
      },
      err => {
        console.log(err)
      }
    )
  }
   
  

}
