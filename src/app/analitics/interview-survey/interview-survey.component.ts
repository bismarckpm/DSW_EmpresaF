import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/core/services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';

export interface DataItem  {
  pregunta: {id};
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
  selector: 'app-interview-survey',
  templateUrl: './interview-survey.component.html',
  styleUrls: ['./interview-survey.component.scss']
})
export class InterviewSurveyComponent implements OnInit {

  preguntas:any;
  opciones:any;
  respuestaForm: FormGroup;
  radioSelected:any;
  checked:any;
  checkedIDs:any;
  arraytest:any;
  sub: any;
  id: number;
  auxPreguntas: any =[];
  idEncuestado:number;
  respuesta:any = [];
  multipleSelected:any;
  first: boolean = false;
  second: boolean = false;
  constructor(private router: Router, private userService:UsersService,public _snackBar: MatSnackBar,private formBuilder: FormBuilder,private route: ActivatedRoute) { }

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
    this.multipleSelected = {

    }
    this.auxPreguntas = []
  }

  getIdEncuestado(){
    this.sub = this.route.params.subscribe(params => {
    let encuestado = +params['id'];
    this.idEncuestado = encuestado;
    })
  }

  getPreguntas(){
    let encuestadoStorage = localStorage.getItem('encuesta');
    let encuestado = JSON.parse(encuestadoStorage);
    this.id = encuestado.encuestaId; 
    let analistStorage = localStorage.getItem('analistaLogged');
    let analista = JSON.parse(analistStorage);
    let token = analista.token;
    this.userService.getPreguntaEncuesta(this.id,token)
      .subscribe(
        res => {
          let auxRes:any;
          auxRes = res;
          console.log(auxRes)
          if(auxRes.estado == 'success'){
            this.preguntas = auxRes.data;
            for (let item of this.preguntas ){
              this.auxPreguntas.push(item.idPregunta)
            }
          }
        },
        err => {
          console.log(err)
        }
      )
  }

  private readonly obj: ObjType = {
    data: []
  };

  private readonly auxObj: auxObjType = {
    data: []
  };

  handleRespuesta(){
    let i:number = 0;
    for(let item in this.radioSelected){
        if(this.radioSelected[item].descripcion != undefined && this.radioSelected[item].idOpcion != undefined ){
          const dataCopy : DataItem = {
            pregunta: {id:this.radioSelected[item].idPregunta},
            descripcion: "",
            rango: 0,
            encuestado : {id:this.idEncuestado},
            opciones: [{id:this.radioSelected[item].idOpcion}],
          };
          this.obj.data[i] = dataCopy;
        }
        
      i++; 
    }
    
    console.log(this.obj.data)
      /*for(let item in this.obj.data){
        const auxDataCopy : respuestFormulada = {
          pregunta: {id:this.auxPreguntas[j]},
          descripcion: this.obj.data[item].descripcion,
          rango: 0,
          encuestado : {id:this.idEncuestado},
          opciones: this.obj.data[item].opciones ,
        };
        this.auxObj.data[j] = auxDataCopy;
        j++
      }*/
      //console.log(this.auxObj.data)
      if(this.obj.data.length > 0){
        let analistStorage = localStorage.getItem('analistaLogged');
        let analista = JSON.parse(analistStorage);
        let token = analista.token;
        this.userService.respuestaEncuesta(this.obj.data,this.id,token) 
        .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            console.log(auxRes)
            if(auxRes.estado == 'success'){
              this.openSnackBar("Encuesta respondida");
              this.router.navigate(['analitics/myStudies']);
              console.log('en proceso')
              this.first= true;
            }
          },
          err => {
            console.log(err)
          }
        )
      }
  }


}
