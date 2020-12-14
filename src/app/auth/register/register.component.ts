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
  zones: any;
  generos:any;
  estadosCiviles:any;
  registerEncuestadoForm: FormGroup;
  registerClienteForm:FormGroup;
  registro_usuario:boolean = true;

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
  parroquia:any;
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
      parroquia: ['',Validators.required],
      nivelEstudio: ['',Validators.required],
      nivelSocioeconomico: ['',Validators.required],
      telefono: ['',Validators.required],
      nombreUsuario: ['',Validators.required],
      contrasena: ['',Validators.required],
    });
    this.registerClienteForm = this.formBuilder.group({
      nombre:['', Validators.required],
      nombreUsuario: ['',Validators.required],
      contrasena: ['',Validators.required],
    })
    this.getUbication();
    this.getGenero();
    this.getEstadoCivil();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getUbication(){
    this.zones = [
      { name: 'Los Caobos Av La Salle'},
      { name: 'Las Palmas Av Las Palmas' },
      { name: 'La Florida Av Andres Bello'},
    ]
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

  handleRegisterEncuestado(){
    this.numeroIdentificacion = this.registerEncuestadoForm.get('numeroIdentificacion').value;
    this.primerNombre = this.registerEncuestadoForm.get('primerNombre').value
    this.primerApellido = this.registerEncuestadoForm.get('primerApellido').value;
    this.direccionComplemento = this.registerEncuestadoForm.get('direccionComplemento').value
    this.fechaNacimiento = this.registerEncuestadoForm.get('fechaNacimiento').value
    this.genero = this.registerEncuestadoForm.get('genero').value
    this.estadoCivil =   this.registerEncuestadoForm.get('estadoCivil').value
    this.ocupacion = this.registerEncuestadoForm.get('ocupacion').value
    this.parroquia = this.registerEncuestadoForm.get('parroquia').value
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
                                  this.parroquia,
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

  handleRegisterCliente(){
    this.nombre = this.registerClienteForm.get('nombre').value;
    this.nombreUsuario = this.registerClienteForm.get('nombreUsuario').value
    this.contrasena = this.registerClienteForm.get('contrasena').value
    this.userService.registerCliente(this.nombre,this.nombreUsuario,this.contrasena)
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
