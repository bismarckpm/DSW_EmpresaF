import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-analysis',
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.scss']
})
export class AnalysisComponent implements OnInit {
  preguntas:any;
  opciones:any;
  respuestaForm: FormGroup;
  constructor(private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.respuestaForm = this.formBuilder.group({
      Respuesta: ['', Validators.required],
    });
    this.getPreguntas();


  }
//Puede ser solo la descripcion de la opcion mas comun
  getPreguntas(){
    this.preguntas = [
      { idPregunta:1,
        descripcionPregunta:'El aguacate que es?',
        tipo:'Seleccion simple',
        opciones: [{idOpcion:1,descripcion:'Potacsio, blanco y maduro'}]
      },
      { idPregunta:2,
        descripcionPregunta:'El gris es la union de negro y blanco?',
        tipo:'Verdadero o falso',
        opciones: [{idOpcion:4,descripcion:'Verdadero'}]
      },
      { idPregunta:3,
        descripcionPregunta:'Como se entero acerca del negocio',
        tipo:'Completacion',
        opciones: [{idOpcion:6,descripcion:null}]
      },
      { idPregunta:8,
        descripcionPregunta:'Esta de acuerdo con el negocio?',
        tipo:'rango',
        opciones: [{idOpcion:6,descripcion:'Muy de acuerdo'}]
      },
      { idPregunta:4,
        descripcionPregunta:'El cielo es azul?',
        tipo:'Verdadero o falso',
        opciones: [{idOpcion:8,descripcion:'Falso'}]
      },
      { idPregunta:5,
        descripcionPregunta:'El agua es?',
        tipo:'Seleccion multiple',
        opciones: [{idOpcion:10,descripcion:'Liquido'}]
      },
      { idPregunta:6,
        descripcionPregunta:'Que es verde?',
        tipo:'Seleccion multiple',
        opciones: [{idOpcion:13,descripcion:'Las hojas'}]
      },
      { idPregunta:7,
        descripcionPregunta:'Esta de acuerdo con los servicios?',
        tipo:'rango',
        opciones: [{idOpcion:4,descripcion:'neutral'}]
      }

    ]

  }

  saveData(){
    if (this.respuestaForm.valid) {
      console.log(this.respuestaForm.value);
    } else {
      alert("Debe llenar el campo con una conclusion.");
    }
  }
}
