import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router,ActivatedRoute } from '@angular/router';
import { AnalystService } from 'src/app/core/services/analyst.service'

@Component({
  selector: 'app-finish-study',
  templateUrl: './finish-study.component.html',
  styleUrls: ['./finish-study.component.scss']
})
export class FinishStudyComponent implements OnInit {
  finishStudyForm: FormGroup;
  resultado: string;
  idStudio:number;
  sub:any;
  constructor(private router: Router,private analystService:AnalystService,private formBuilder: FormBuilder,public _snackBar: MatSnackBar,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.finishStudyForm = this.formBuilder.group({
      Resultado: ['', Validators.required]
    });
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  handleFinishStudy(){
    this.resultado = this.finishStudyForm.get('Resultado').value;
    this.sub = this.route.params.subscribe(params => {
      this.idStudio = +params['id'];
    })
    let analistStorage = localStorage.getItem('analistaLogged');
    let analista = JSON.parse(analistStorage);
    let token = analista.token;
    console.log(this.idStudio+" "+this.resultado)
    this.analystService.finishStudy(this.idStudio,this.resultado,token)
    .subscribe(
      res => {
        let auxRes:any;
        auxRes = res;
        console.log(auxRes)
        if(auxRes.estado == 'success'){
          this.openSnackBar("Estudio finalizado");
          this.router.navigate(['/analitics/myStudies']);
        }
      },
      err => {
        console.log(err)
      }
    )
  }
}
