import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup} from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-subcategory',
  templateUrl: './updateSubcategory.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateSubcategoryComponent implements OnInit {
  updateSubcategoriaForm: FormGroup;
  nombre:any;
  categoria:any;
  sub: any;
  id: number;
  oldnombre:any;
  oldcategoria: any;
  oldcategoriaId:any;
  categorias:any;
  constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder, private adminService:AdminService,public _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.updateSubcategoriaForm = this.formBuilder.group({
      nombre:[''],
      categoria:[''],
    })
    this.getInfo();
    this.getCategorias();
  }

  getInfo(){
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.adminService.getSubcategoria(this.id).
      subscribe(
        res =>{
          let auxRes:any = res;
          this.oldnombre = auxRes.nombreSubcategoria;
          this.oldcategoria = auxRes.categoriaNombre;
          this.oldcategoriaId = auxRes.categoriaId;
        },
        err =>{
          console.log(err)
        }
      )
    });
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getCategorias(){
    this.adminService.getCategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.categorias = auxRes.categorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }



  handleUpdateSubategoria(){
    let val=0;
    this.nombre = this.updateSubcategoriaForm.get('nombre').value;
    if (!this.nombre){
      this.nombre = this.oldnombre;
      val++;
    }
    this.categoria = this.updateSubcategoriaForm.get('categoria').value;
    if (!this.categoria){
      this.categoria = this.oldcategoriaId;
      val++;
    }
    if(val!=2){
      this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        this.adminService.updateSubcategoria(this.nombre,this.categoria,this.id)
        .subscribe(
          res => {
            let auxRes:any = res;
            if(auxRes.estado == 'success'){
              this.openSnackBar("Actualización exitosa");
              this.router.navigate(['/config/menusubcategory']);
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
