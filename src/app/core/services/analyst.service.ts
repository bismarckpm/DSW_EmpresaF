import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AnalystService extends ApiService{

  getSolicitudes(idAnalista:number){
    return this.http.get(this.API_URL+'api/analista/getsolicitudespendientes/'+idAnalista,this.httpOptions);
  }

  activarSolicitud(idSolicitud:number){
    return this.http.put(this.API_URL+'api/analista/activarestudio/'+idSolicitud,this.httpOptions)
  }

  getMyStudies(idAnalista:number){
    return this.http.get(this.API_URL+'api/analista/obtenerestudios/'+idAnalista,this.httpOptions);
  }

  getRespuestaEncuesta(idEncuesta:number){
    return this.http.get(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,this.httpOptions)
  }
}
