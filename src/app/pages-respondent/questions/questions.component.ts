import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/core/services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';

export interface DataItem  {
  descripcion: string;
  rango: number;
  encuestado: {};
  opciones: [{}];
};

export interface respuestFormulada  {
  pregunta: {};
  descripcion: string;
  rango: number;
  encuestado: {};
  opciones: [{}];
};

type ObjType = {
  data: DataItem[]
};

type auxObjType = {
  data: respuestFormulada[]
};

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})

export class QuestionsComponent implements OnInit {
  preguntas:any;
  opciones:any;
  respuestaForm: FormGroup;
  radioSelected:any;
  checked:any;
  checkedIDs:any;
  arraytest:any;
  sub: any;
  id: number;
  auxPreguntas: any;
  idEncuestado:string;
  respuesta:any = [];
  constructor(private userService:UsersService,public _snackBar: MatSnackBar,private formBuilder: FormBuilder,private route: ActivatedRoute) { }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
      panelClass: ['blue-snackbar']
    });
  }

  ngOnInit(): void {
    this.respuestaForm = this.formBuilder.group({
      Respuesta: ['', Validators.required],
    });
    this.getPreguntas();
    this.getIdEncuestado();
    this.radioSelected = {

    }
    this.auxPreguntas = []
  }

  getIdEncuestado(){
    let encuestadoStorage = localStorage.getItem('encuestadoLogged');
    let encuestado = JSON.parse(encuestadoStorage);
    encuestado = encuestado.id; 
    this.userService.getIdEncuestado(encuestado)
        .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            this.idEncuestado = auxRes.idEncuestado;
          },
          err => {
            console.log(err)
          }
        )
  }

  getPreguntas(){
    this.sub = this.route.params.subscribe(params => {
    this.id = +params['id']; // (+) converts string 'id' to a number
    this.userService.getPreguntaEncuesta(this.id )
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.preguntas = auxRes.data;
            for (let item of this.preguntas ){
              this.auxPreguntas.push(item.id)
            }
          }
        },
        err => {
          console.log(err)
        }
      )
    })
  }

  private readonly obj: ObjType = {
    data: []
  };

  private readonly auxObj: auxObjType = {
    data: []
  };

  handleRespuesta(){
    this.checkedIDs =  { 
      opciones: this.radioSelected
    }
    let i:number = 0;
    for(let item in this.radioSelected){
        if(this.radioSelected[item].descripcion == undefined && this.radioSelected[item].idOpcion == undefined){
          const dataCopy : DataItem = {
            descripcion: this.radioSelected[item],
            rango: 0,
            encuestado : {id:this.idEncuestado},
            opciones: [{id:item}],
          };
          this.obj.data[i] = dataCopy;
        }
        else {
        const dataCopy : DataItem = {
          descripcion: "",
          rango: 0,
          encuestado : {id:this.idEncuestado},
          opciones: [{id:this.radioSelected[item].idOpcion}],
        };
        this.obj.data[i] = dataCopy;
      }
      i++; 
    }
    
    this.sub = this.route.params.subscribe(params => {
    this.id = +params['id'];
    })
    let j = 0;
    if(this.obj.data.length == this.auxPreguntas.length){
      for(let item in this.obj.data){
        const auxDataCopy : respuestFormulada = {
          pregunta: {id:this.auxPreguntas[j]},
          descripcion: this.obj.data[item].descripcion,
          rango: 0,
          encuestado : {id:this.idEncuestado},
          opciones: this.obj.data[item].opciones ,
        };
        this.auxObj.data[j] = auxDataCopy;
        j++
        
      }
      this.userService.respuestaEncuesta(this.auxObj.data,this.id) 
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Encuesta respondida");
          }
        },
        err => {
          console.log(err)
        }
      )
    }
    else {
      this.openSnackBar("Responda todas las preguntas");
    }
  }
}
