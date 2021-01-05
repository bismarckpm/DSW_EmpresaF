import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { AdminService } from 'src/app/core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-addquestion',
  templateUrl: './addquestion.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddQuestionComponent implements OnInit{
  auxRes: any;
  createPreguntaForm: FormGroup;
  admin: any;
  descripcionPregunta:string
  tipoPregunta:string
  min:number
  max:number
  opciones:any
  tipos:any;
  token: string
  constructor(private formBuilder: FormBuilder,private adminService: AdminService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.createPreguntaForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      selectTipo: ['', Validators.required],
      itemRows: this.formBuilder.array([this.initItemRows()])
    });
    this.getPregunta();
    //Agregar opciones dinamicas*/
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleCreatePregunta(){
    this.descripcionPregunta = this.createPreguntaForm.get('Nombre').value;
    this.tipoPregunta = this.createPreguntaForm.get('selectTipo').value;
    this.min = 0;
    this.max = 0;
    /*this.adminService.createQuestion(this.descripcionPregunta,this.tipoPregunta,this.min,this.max,this.opciones)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Pregunta creada exitosamente");
        }
        else {
          this.openSnackBar("Ocurrio un problema");
        }
      },
      err => {
        console.log(err)
      }
    )*/
  } 

  createQuestion(){
    this.descripcionPregunta = this.createPreguntaForm.get('Nombre').value;
    this.tipoPregunta = this.createPreguntaForm.get('selectTipo').value;
    this.min = 0;
    this.max = 0;
    this.opciones = this.createPreguntaForm.get('itemRows').value;
    if(this.tipoPregunta == "desarrollo"){
      this.opciones = null;
    }
    this.adminService.createQuestion(this.descripcionPregunta,this.tipoPregunta,this.min,this.max,this.opciones)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.openSnackBar("Pregunta creada exitosamente");
          window.location.reload();
        }
        else {
          this.openSnackBar("Ocurrio un problema");
        }
      },
      err => {
        console.log(err)
      }
    )
  }

  getPregunta(){
    this.tipos = [
     
      { idPregunta:2, name: 'multiple'},
      { idPregunta:3, name: 'simple'},
      { idPregunta:4, name: 'desarrollo'},
    ]
  }
  
  //Agregar opciones dinamicas
  initItemRows() {
    return this.formBuilder.group({
      descripcion: ['']
    });
  }

  get formArr() {
    return this.createPreguntaForm.get('itemRows') as FormArray;
  }

  addNewRow() {
    this.formArr.push(this.initItemRows());
  }

  deleteRow(index: number) {
    this.formArr.removeAt(index);
  }
  //--------------------------
}