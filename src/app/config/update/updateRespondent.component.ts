import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-updateRespondent',
  templateUrl: './updateRespondent.component.html',
  styleUrls: ['./update.component.scss']
})

export class UpdateRespondentComponent implements OnInit {
    updateRespondtForm: FormGroup;
    numiden: any;
    nombre:any;
    apellido:any;
    genero:any;
    estadocivil:any;
    ocupacion:any;
    parroquia: any;
    parroquiaId:any;
    cod:any;
    tel:any;
    oldnumiden: any;
    oldnombre:any;
    oldapellido:any;
    oldgenero:any;
    oldestadocivil:any;
    oldocupacion:any;
    oldparroquia: any;
    oldparroquiaId:any;
    Parroquias:any;
    generos:any;
    estadosCiviles:any;
    val:number;
    sub: any;
    id: number;
    constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


    ngOnInit(): void {
      this.updateRespondtForm = this.formBuilder.group({
        numeroIdentificacion:[''],
        primerNombre:[''],
        primerApellido:[''],
        genero:[''],
        estadoCivil:[''],
        ocupacion:[''],
        Selectparroquia: [''],
        })
      this.getRespondant();
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
  
    getRespondant(){
        this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        this.adminService.getEncuestado(this.id).
        subscribe(
          res =>{
            let auxRes:any = res;
            this.oldnumiden = auxRes.numero_de_identificacion;
            this.oldnombre = auxRes.primer_nombre;
            this.oldapellido = auxRes.primer_apellido;
            this.oldgenero = auxRes.genero;
            this.oldestadocivil = auxRes.estadoCivil;
            this.oldocupacion = auxRes.ocupacion;
            this.oldparroquia = auxRes.parroquia;
            this.oldparroquiaId = auxRes.parroquiaId;
            this.tel = auxRes.telefonos[0].numeroTelefono;
            this.cod = auxRes.telefonos[0].codigoArea;
          },
          err =>{
            console.log(err)
          }
        )
        });
    }

    getParroquia(){
        this.adminService.getParroquias()
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
  
    handleUpdateEncuestado(){
      this.val=0;
      this.numiden = this.updateRespondtForm.get('numeroIdentificacion').value;
      if (!this.numiden){
          this.numiden = this.oldnumiden;
          this.val++;
      }
      this.nombre = this.updateRespondtForm.get('primerNombre').value;
      if (!this.nombre){
          this.nombre = this.oldnombre;
          this.val++;
      }
      this.apellido = this.updateRespondtForm.get('primerApellido').value;
      if (!this.apellido){
          this.apellido = this.oldapellido;
          this.val++;
      }
      this.genero = this.updateRespondtForm.get('genero').value;
      if (!this.genero){
          this.genero = this.oldgenero;
          this.val++;
      }
      this.estadocivil = this.updateRespondtForm.get('estadoCivil').value;
      if (!this.estadocivil){
          this.estadocivil = this.oldestadocivil;
          this.val++;
      }
      this.ocupacion = this.updateRespondtForm.get('ocupacion').value;
      if (!this.ocupacion){
          this.ocupacion = this.oldocupacion;
          this.val++;
      }
      this.parroquiaId = this.updateRespondtForm.get('Selectparroquia').value;
      if (!this.parroquiaId){
          this.parroquiaId = this.oldparroquiaId;
          this.val++;
      }
      if(this.val!=7){
          this.sub = this.route.params.subscribe(params => {
          this.id = +params['id'];
            this.adminService.updateEncuestado(this.numiden,this.nombre,this.apellido,this.genero,this.estadocivil,this.ocupacion,this.parroquiaId,this.cod,this.tel,this.id)
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
            });
          /*console.log(this.numiden,this.nombre,this.apellido,this.genero,this.estadocivil,this.ocupacion,this.parroquiaId,this.usuario,codigo,numero,this.idEncuestado)*/
      }else{
        this.openSnackBar("Debe ingresar al menos un campo para realizar la modificación");
      }
    }
}