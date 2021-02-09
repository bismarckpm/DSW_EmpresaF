import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-brand',
  templateUrl: './updateBrand.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateBrandComponent implements OnInit {
  updateMarcaForm: FormGroup;
  nombre:any;
  subcategoria:any;
  tipo:any;
  capacidad:any;
  unidad:any;
  sub: any;
  id: number;
  oldnombre:any;
  oldsubcategoria: any;
  oldsubcategoriaId:any;
  oldtipo:any;
  oldcapacidad:any;
  oldunidad:any;
  subcategorias:any;
  constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.updateMarcaForm = this.formBuilder.group({
      nombre:[''],
      subcategoria:[''],
      tipo:[''],
      capacidad:[''],
      unidad:[''],
    })
    this.getSubcategorias();
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
      this.adminService.getMarca(this.id).
      subscribe(
        res =>{
          let auxRes:any = res;
          this.oldnombre = auxRes.nombreMarca;
          this.oldsubcategoria = auxRes.subcategoriaNombre;
          this.oldsubcategoriaId = auxRes.subcategoriaId;
          this.oldtipo = auxRes.tipoMarca;
          this.oldcapacidad = auxRes.capacidad;
          this. oldunidad = auxRes.unidad;
        },
        err =>{
          console.log(err)
        }
      )
    });
    
    
  }

  getSubcategorias(){
    this.adminService.getSubcategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.subcategorias = auxRes.subcategorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  handleUpdateMarca(){
    let val=0;
    let adminStorage = localStorage.getItem('administrador');
    let admin = JSON.parse(adminStorage);
    let token = admin.token;
    this.nombre = this.updateMarcaForm.get('nombre').value;
    if (!this.nombre){
      this.nombre = this.oldnombre;
      val++;
    }
    this.subcategoria = this.updateMarcaForm.get('subcategoria').value;
    if (!this.subcategoria){
      this.subcategoria = this.oldsubcategoriaId;
      val++;
    }
    this.tipo = this.updateMarcaForm.get('tipo').value;
    if (!this.tipo){
      this.tipo = this.oldtipo;
      val++;
    }
    this.capacidad = this.updateMarcaForm.get('capacidad').value;
    if (!this.capacidad){
      this.capacidad = this.oldcapacidad;
      val++;
    }
    this.unidad = this.updateMarcaForm.get('unidad').value;
    if (!this.unidad){
      this.unidad = this.oldunidad;
      val++;
    }
    if(val!=5){
      this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        console.log(this.nombre,this.tipo,this.capacidad,this.unidad,this.subcategoria,this.id)
        this.adminService.updateMarca(this.nombre,this.tipo,this.capacidad,this.unidad,this.subcategoria,this.id,token)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/config/menubrand']);
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
