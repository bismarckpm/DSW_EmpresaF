import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from 'src/app/core/services/session.service';
import { Router } from '@angular/router';

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
              private router: Router,) { }

  ngOnInit(): void {
    this.ValidateForms();
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
          this.router.navigate(['config/menuconfig']);
          return;
        }
        console.log(res);
      },
      err => {
        console.log(err);
      }
    )
  } 
}
