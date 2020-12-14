import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from 'src/app/core/services/session.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
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

  checkingInputEmail(){
    if(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/igm.test(this.signInForm.get('Email').value)){
      return true;
    }
   
  }
  ValidateForms(){
    this.signInForm = this.formBuilder.group({
      Email: ['', Validators.required]
    });
  }

  handleSignIn(){
    this.bSignIn = true;
    this.sessionService.restartUserPassword(this.signInForm.get('Email').value).subscribe(
      res => {
        this.bSignIn = false;
        if (this.checkingInputEmail()){
          let auxRes:any;
          auxRes = res;
          if(auxRes.estado == 'success'){
            this.openSnackBar("Se ha enviado a su correo una nueva contraseña");
            this.router.navigate(['auth/login']);
          }else if(auxRes.estado == 'error'){
            this.openSnackBar(auxRes.mensaje);
          }
          console.log(res);
        } else {
          this.bSignIn = false;
          this.openSnackBar("No se pudo validar el Email");
        }
      },
      err => {
        this.openSnackBar(err);
      }
    ) 
  } 
}
