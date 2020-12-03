import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.signInForm = this.formBuilder.group({
      Email: ['', Validators.required],
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
    
  } 
}