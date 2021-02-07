import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

export interface DataItem  {
 id:any;
};

export interface preguntasItem  {
  descripcionPregunta: string
  tipoPregunta: string;
  min:number;
  max:number;
  opciones:any;
};

type ObjType = {
  data: DataItem[]
};

type ObjPregunta = {
  data: preguntasItem[]
};


@Component({
  selector: 'app-renovated-study',
  templateUrl: './renovated-study.component.html',
  styleUrls: ['./renovated-study.component.scss']
})
export class RenovatedStudyComponent implements OnInit {
  isEditable = false;
  seleccion_pregunta:boolean = true;
  idSolicitud:any;
  sub:any;
  /*Atributos del estudio*/
  estudioFormGroup: FormGroup;
  nombreEstudio: string;
  /*Atributos de la encuesta*/
  subCategorias:any; 
  nombreCategoria:string;
  Idsubcategoria:number;
  encuestaFormGroup: FormGroup;
  nombreEncuesta: string;
  nombreSubcategoria: any;
  /*Atributos de preguntas*/
  preguntaFormGroup:any;
  preguntas:any;
  questionsSelected:any;
  createPreguntaForm: FormGroup;
  descripcionPregunta:string
  tipoPregunta:string
  min:number
  max:number
  opciones:any;
  tipos:any;

  survey:FormGroup
  constructor(private formBuilder: FormBuilder,private adminService:AdminService,public _snackBar: MatSnackBar,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.estudioFormGroup = this.formBuilder.group({
      Nombre: ['', Validators.required]
    });
    this.encuestaFormGroup = this.formBuilder.group({
      Nombre: ['', Validators.required],
    });
    this.preguntaFormGroup = this.formBuilder.group({
      id: ['', Validators.required]
    });
 
    this.survey = new FormGroup({
      sections: new FormArray([
        this.initSection(),
      ]),
    });
    this.getPregunta();
    this.getSubCategoria();
    this.getExistingQuestions();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  getSubCategoria(){
    this.adminService.getSubcategorias()
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.subCategorias = auxRes.subcategorias;
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  getExistingQuestions(){
    /*this.adminService.getQuestionsSu(this.idSolicitud)  
        .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){
              this.preguntas = auxRes.preguntas;
              console.log('preguntas su');
              if (auxRes.data.length==0){
                this.adminService.getQuestions()
                  .subscribe(
                    res => {
                      let auxRes:any;
                      auxRes = res;
                      if(auxRes.estado == 'success'){
                        this.preguntas = auxRes.preguntas;
                        console.log('preguntas');
                      }
                    },
                    err => {
                      console.log(err)
                    }
                  )
              }
            }
          },
          err => {
            console.log(err)
          }
        )*/
    
    this.adminService.getQuestions()
    .subscribe(
      res => {
        let auxRes:any
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.preguntas = auxRes.data;
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

  selectQuestions(questionSelected){
    this.questionsSelected = [];
    for(let i = 0; i < questionSelected.length; i++){
      for(let j = 0; j < this.preguntas.length; j++){
        if(questionSelected[i] == this.preguntas[j].preguntaId){
          this.questionsSelected.push(this.preguntas[j]);
          const dataCopy : DataItem = {
            id: this.preguntas[j].preguntaId
          };
          this.obj.data[i] = dataCopy;
        }
      } 
    }
  }

  //Agregar opciones dinamicas
  initSection() {
    return new FormGroup({
      Nombre: new FormControl('',Validators.required),
      tipoPregunta: new FormControl('',Validators.required),
      opciones: new FormArray([
        this.initQuestion()
        ])
    });
  }
  initQuestion() {
    return new FormGroup({
      descripcion: new FormControl('')
    });
  }
  addSection() {
    const control = <FormArray>this.survey.get('sections');
    control.push(this.initSection());
  }

  addQuestion(j){
    const formArray = this.survey.get('sections') as FormArray;
    const secondArray = formArray.controls[j];
    const control = secondArray.get('opciones') as FormArray;
    // console.log(control);
    control.push(this.initQuestion());
  }
 getSections(form) {
    //console.log(form.get('sections').controls);
    return form.controls.sections.controls;
  }
  getQuestions(form) {
   //console.log(form.controls.questions.controls);
    return form.controls.opciones.controls;
  }

  removeQuestion(j){
    const formArray = this.survey.get('sections') as FormArray;
    const secondArray = formArray.controls[j];
    const control = secondArray.get('opciones') as FormArray;
    control.removeAt(j);
 }
  //---------------------------------
  getPregunta(){
    this.tipos = [
     
      { idPregunta:2, name: 'multiple'},
      { idPregunta:3, name: 'simple'},
      { idPregunta:4, name: 'desarrollo'},
    ]
  }

  handleCreatePregunta(){
    this.min = 0;
    this.max = 0;
  } 

  oneMoreQuestion(){

  }
  private readonly objPregunta: ObjPregunta = {
    data: []
  };
  createStudy(){
    this.nombreEstudio = this.estudioFormGroup.get('Nombre').value;
    this.nombreEncuesta = this.encuestaFormGroup.get('Nombre').value;
    this.sub = this.route.params.subscribe(params => {
      this.idSolicitud = +params['id'];
    })
    if(this.seleccion_pregunta ==  true){
      let preguntasElegidas:any = this.obj.data;
      this.adminService.newCreateStudy(this.nombreEstudio,this.nombreEncuesta,preguntasElegidas,this.idSolicitud,this.seleccion_pregunta)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Estudio creado con exito");
            location.reload();
          }
        },
        err => {
          console.log(err)
        }
      )
    }
    else if(this.seleccion_pregunta ==  false){
      let i:number = 0;
      this.preguntas = this.survey.get('sections').value;
      for(let item in this.preguntas){
        if(this.preguntas[item].tipoPregunta == 'desarrollo'){
          this.preguntas[item].opciones = null;
        } 
        const dataCopy : preguntasItem = {
          descripcionPregunta: this.preguntas[item].Nombre,
          tipoPregunta: this.preguntas[item].tipoPregunta,
          min:0,
          max:0,
          opciones:this.preguntas[item].opciones
        };
        this.objPregunta.data[i] = dataCopy;
        i++
      }
      //console.log(this.objPregunta.data)
      this.adminService.newCreateStudy(this.nombreEstudio,this.nombreEncuesta,this.objPregunta.data,this.idSolicitud,this.seleccion_pregunta)
      .subscribe(
        res => {
          let auxRes:any = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Estudio creado con exito");
            location.reload();
            //this.createPreguntaForm.reset();
          }
        },
        err => {
          console.log(err)
        }
      )
    }
  }
  
}
