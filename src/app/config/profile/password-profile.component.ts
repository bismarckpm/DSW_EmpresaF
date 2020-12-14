import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-password-profile',
  templateUrl: './password-profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class PasswordProfileComponent implements OnInit {
  passwordForm: FormGroup;


  constructor(private formBuilder: FormBuilder,private router: Router) {

      this.passwordForm = this.formBuilder.group({
       oldPassword: ['', Validators.required],
       Password1: ['', Validators.required],
       Password2: ['', Validators.required]
      });

  }

  ngOnInit(): void {
  }

  saveData(){
    if (this.passwordForm.valid) {
      console.log(this.passwordForm.value);
    } else {
      alert("Debe llenar todos los campos.");
    }
  }


  }
