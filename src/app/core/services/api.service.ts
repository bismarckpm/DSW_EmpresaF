import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  API_URL = environment.apiUrl;

  httpOptions:any = new Headers({'Content-Type': 'application/json'})


  constructor(protected http: HttpClient) { }
}
