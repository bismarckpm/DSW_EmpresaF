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
  auxPreguntas: any =[];
  idEncuestado:string;
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
    let encuestadoStorage = localStorage.getItem('encuestadoLogged');
    let encuestado = JSON.parse(encuestadoStorage);
    let token = encuestado.token;
    encuestado = encuestado.id; 
    this.userService.getIdEncuestado(encuestado,token)
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
    let encuestadoStorage = localStorage.getItem('encuestadoLogged');
    let encuestado = JSON.parse(encuestadoStorage);
    let token = encuestado.token;
    this.id = +params['id']; // (+) converts string 'id' to a number
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
    })
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
    let solicitudId;
    this.sub = this.route.params.subscribe(params => {
    this.id = +params['id'];
    solicitudId = +params['solicitudId'];
    })
    
   
    
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
      }
      console.log(this.auxObj.data)*/
      if(this.obj.data.length > 0){
        let encuestadoStorage = localStorage.getItem('encuestadoLogged');
        let encuestado = JSON.parse(encuestadoStorage);
        let token = encuestado.token;
        this.userService.respuestaEncuesta(this.obj.data,this.id,solicitudId,token) 
        .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            console.log(auxRes)
            if(auxRes.estado == 'success'){
              this.openSnackBar("Encuesta respondida");
              this.router.navigate(['/pages-respondent/respondent']);
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
