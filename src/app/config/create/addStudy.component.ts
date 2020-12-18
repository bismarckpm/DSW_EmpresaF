import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-addstudy',
  templateUrl: './addstudy.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddStudyComponent implements OnInit{
  auxRes: any;
  createStudyForm: FormGroup;
  nombreCategoria: string;
  token: string;
  encuestas:any;
  nombre:string;
  idEncuesta:number;
  constructor(private formBuilder: FormBuilder,private adminService:AdminService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createStudyForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      selectEncuesta: ['', Validators.required]
    });
    this.getEncuestas();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleCreateStudy(){
    this.nombre = this.createStudyForm.get('Nombre').value;
    this.idEncuesta = this.createStudyForm.get('selectEncuesta').value;
    this.adminService.createStudy(this.nombre,this.idEncuesta)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Estudio creado con exito");
        }
        else if(auxRes.estado != 'success'){
          this.openSnackBar("Ocurrio un problema");
        }
      },
      err => {
        console.log(err)
      }
    )
  }
  
  getEncuestas(){
    this.adminService.getEncuesta()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.encuestas = auxRes.encuestas;
        }
      },
      err => {
        console.log(err)
      }
    )
  }
}