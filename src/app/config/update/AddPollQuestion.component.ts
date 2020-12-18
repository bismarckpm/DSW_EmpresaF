import { Component,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from './../../core/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pollquestion',
  templateUrl: './AddPollQuestion.component.html',
  styleUrls: ['./AddPollQuestion.component.scss']
})


export class AddPollQuestionComponent implements OnInit{
  sub: any;
  id: number;
  auxRes: any;
  EncuestaPreguntaForm: FormGroup;
  admin: any;
  Preguntas:any;
  token: string;
  IdPregunta:number;
  constructor(private router: Router,private route: ActivatedRoute,private formBuilder: FormBuilder,private adminService:AdminService,public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.EncuestaPreguntaForm = this.formBuilder.group({
      selectPregunta: ['',Validators.required]
    });
    this.getPreguntas();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handlePollQuestion(){
    this.sub = this.route.params.subscribe(params => {
        this.id = +params['id'];
        this.IdPregunta =  this.EncuestaPreguntaForm.get('selectPregunta').value;
        this.adminService.addQuestiontoPoll(this.id,this.IdPregunta)
        .subscribe(
          res => {
            let auxRes:any;
            auxRes = res;
            if(auxRes.estado == 'success'){          
            this.openSnackBar("Pregunta añadida");
            this.router.navigate(['/config/updatePoll/'+this.id]);
            }
          },
          err => {
            console.log(err)
          }
        )
      });
  } 

  getPreguntas(){
    this.adminService.getQuestions()/*cambiar por get de preguntas que no esten en la encuesta*/
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        if(auxRes.estado == 'success'){
          this.Preguntas = auxRes.data;
        }
      },
      err => {
        console.log(err)
      }
    )
  }
  
}