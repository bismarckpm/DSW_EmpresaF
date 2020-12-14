import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/core/services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  constructor(private userService:UsersService,public _snackBar: MatSnackBar,private formBuilder: FormBuilder) { }

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
    this.radioSelected = {
    }

  }

  getPreguntas(){
    this.preguntas = [
      { idPregunta:1, 
        descripcionPregunta:'El aguacate que es?', 
        tipo:'Seleccion simple',
        opciones: [{idOpcion:1,descripcion:'Verdura'},{idOpcion:2,descripcion:'Fruta'},{idOpcion:3,descripcion:'Hortaliza'}]
      },
      { idPregunta:2,
        descripcionPregunta:'El gris es la union de negro y blanco?',
        tipo:'Verdadero o falso',
        opciones: [{idOpcion:4,descripcion:'Verdadero'},{idOpcion:5,descripcion:'Falso'}]
      },
      { idPregunta:3,
        descripcionPregunta:'Como se entero acerca del negocio',
        tipo:'Completacion',
        opciones: [{idOpcion:6,descripcion:null}]
      },
      { idPregunta:4,
        descripcionPregunta:'El cielo es azul?',
        tipo:'Verdadero o falso',
        opciones: [{idOpcion:7,descripcion:'Verdadero'},{idOpcion:8,descripcion:'Falso'}]
      },
      { idPregunta:5,
        descripcionPregunta:'El agua es?',
        tipo:'Seleccion multiple',
        opciones: [{idOpcion:9,descripcion:'H2O'},{idOpcion:10,descripcion:'Liquido'},{idOpcion:11,descripcion:'Un recurso mineral'}]
      },
      { idPregunta:6,
        descripcionPregunta:'Que es verde?',
        tipo:'Seleccion multiple',
        opciones: [{idOpcion:12,descripcion:'El pasto'},{idOpcion:13,descripcion:'Las hojas'}]
      },
    ]
 
  }

  handleRespuesta(){
    console.log(this.radioSelected);
    this.checkedIDs =  { 
      opciones: this.radioSelected
    }
    console.log(this.checkedIDs)
  }
}
