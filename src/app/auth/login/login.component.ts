import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.signInForm = this.formBuilder.group({
      Email: ['', Validators.required],
      Password: ['', Validators.required],
    });
  }
  checkingInputEmail(){
    if(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/igm.test(this.signInForm.get('Email').value)){
      return true;
    }
   
  }
  handleSignIn(){
    this.bSignIn = true;

    let formData = new FormData();
    formData.append('email', this.signInForm.get('Email').value);
    formData.append('password', this.signInForm.get('Password').value);

  } 
}
