import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators , FormArray} from '@angular/forms';
import { UsersService } from 'src/app/core/services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-request-study',
  templateUrl: './request-study.component.html',
  styleUrls: ['./request-study.component.scss']
})
export class RequestStudyComponent implements OnInit {
  requestStudyForm: FormGroup;
  parroquias:any;
  Parroquias:any;
  subcategorias:any;
  nivelS:any;
  generos:any;
  edadInicial:number;
  edadfinal:number;
  genero:string;
  cliente:any;
  parroquia:number;
  subcategoria:number;
  nivelSocioeconomico:number;
  constructor(private formBuilder: FormBuilder, private userService:UsersService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.requestStudyForm = this.formBuilder.group({
      edadInicial: ['', Validators.required],
      edadFinal: ['',Validators.required],
      selectParroquia: ['',Validators.required],
      selectSubcategoria: ['',Validators.required],
      selecNivelS: ['',Validators.required],
      selectGenero: ['',Validators.required],
    });
    this.getSubcategorias();
    this.getParroquia();
    this.getNivelSocioeconomico();
    this.getGeneros();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getSubcategorias(){
    this.userService.getSubcategorias()
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
  
  getNivelSocioeconomico(){
    this.nivelS = [
      {id:1,descripcionS:'baja'},
      {id:2,descripcionS:'media'},
      {id:3,descripcionS:'alta'},
    ]
  }

  handleRequestStudy(){
    this.edadInicial = this.requestStudyForm.get('edadInicial').value;
    this.edadfinal = this.requestStudyForm.get('edadFinal').value;
    this.genero = this.requestStudyForm.get('selectGenero').value;
    let userStorage = localStorage.getItem('clientLogged');
    this.cliente = JSON.parse(userStorage);
    let token = this.cliente.token; 
    this.cliente = this.cliente.id;
    this.parroquia = this.requestStudyForm.get('selectParroquia').value;
    this.subcategoria = this.requestStudyForm.get('selectSubcategoria').value;
    this.nivelSocioeconomico = this.requestStudyForm.get('selecNivelS').value;
    this.userService.requestStudy(this.edadInicial,this.edadfinal,this.genero,this.cliente,this.parroquia,this.subcategoria,this.nivelSocioeconomico,token)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Solicitud enviada con exito");
          window.location.reload();
        }
        else {
          this.openSnackBar(auxRes.mensaje);
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  getGeneros(){
    this.generos = [
      {id:1,descripcion:'masculino'},
      {id:2,descripcion:'femenino'},
      {id:2,descripcion:'ambos'},
    ]
  }
}
