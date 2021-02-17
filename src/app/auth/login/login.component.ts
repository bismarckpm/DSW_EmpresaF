import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from 'src/app/core/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  auxRes: any;
  signInForm: FormGroup;
  hidePassword = true;
  bSignIn = false;
  bCookies = true;
  user: any;
  token: string;
  constructor(private formBuilder: FormBuilder,
              private sessionService:SessionService,
              private router: Router,
              public _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.ValidateForms();
  }

  openSnackBar(message: string){
    this._snackBar.open(message, 'X', {
      duration: 3000,
    });
  }

  ValidateForms(){
    this.signInForm = this.formBuilder.group({
      Email: ['', Validators.required],
      Password: ['', Validators.required]
    });
  }
  checkingInputEmail(){
    if(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/igm.test(this.signInForm.get('Email').value)){
      return true;
    }
   
  }
  handleSignIn(){
    this.bSignIn = true;
    this.sessionService.validateUserCredentials(this.signInForm.get('Email').value,this.signInForm.get('Password').value)
    .subscribe(
      res => {
        this.bSignIn = false;
        let auxRes: any = res
        if(auxRes.estado == 'success'){
          if(auxRes.rol == 'administrador'){
            localStorage.setItem('administrador', JSON.stringify(auxRes))
            this.router.navigate(['config/menuconfig']);
          }
          else if(auxRes.rol == 'cliente'){
            localStorage.setItem('clientLogged', JSON.stringify(auxRes))
            this.router.navigate(['pages/client']);
          }
          else if(auxRes.rol == 'analista'){
            localStorage.setItem('analistaLogged', JSON.stringify(auxRes))
            this.router.navigate(['analitics/menuanalitics']);
          }
          else if(auxRes.rol == 'encuestado'){
            localStorage.setItem('encuestadoLogged', JSON.stringify(auxRes))
            this.router.navigate(['pages-respondent/respondent']);
          }
          return;
        }
        else if(auxRes.estado == 'error'){
          this.bSignIn = false;
          this.openSnackBar('Inicio de sesion invalido');
        }
        console.log(res);
      },
      err => {
        this.bSignIn = false;
        this.openSnackBar('Inicio de sesion invalido');
      }
    )
  } 
}
