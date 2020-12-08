import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';

@Component({
  selector: 'app-addquestion',
  templateUrl: './addquestion.component.html',
  styleUrls: ['./create.component.scss']
})


export class AddQuestionComponent implements OnInit{
  auxRes: any;
  createPreguntaForm: FormGroup;
  admin: any;
  tipos:any;
  token: string;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createPreguntaForm = this.formBuilder.group({
      Nombre: ['', Validators.required],
      selectTipo: ['', Validators.required],
      itemRows: this.formBuilder.array([this.initItemRows()])
    });
    this.getPregunta();
    //Agregar opciones dinamicas*/
   
    
  }
  
  handleCreatePregunta(){
    let formData = new FormData();
    formData.append('Nombre', this.createPreguntaForm.get('Nombre').value);
    console.log(this.createPreguntaForm.get('itemRows').value);
  } 

  getPregunta(){
    this.tipos = [
      { idPregunta:1, name: 'Verdadero o Falso'},
      { idPregunta:2, name: 'Seleccion simple'},
      { idPregunta:3, name: 'Completacion'},
    ]
  }
  
  //Agregar opciones dinamicas
  initItemRows() {
    return this.formBuilder.group({
      Opcion: ['']
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