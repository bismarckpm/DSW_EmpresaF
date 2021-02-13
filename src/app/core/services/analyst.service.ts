import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AnalystService extends ApiService{

  getSolicitudes(idAnalista:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/analista/getsolicitudespendientes/'+idAnalista,this.httpOptions2);
  }

  activarSolicitud(idSolicitud:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/analista/activarestudio/'+idSolicitud,this.httpOptions2)
  }

  getMyStudies(idAnalista:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/analista/obtenerestudios/'+idAnalista,this.httpOptions2);
  }

  getRespuestaEncuesta(idEncuesta:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,this.httpOptions2)
  }

  getSample(idSolicitud:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/muestra/getmuestra/'+idSolicitud,this.httpOptions2)
  }

  getEncuestados(idSolicitud:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/muestra/usuarioagregable/'+idSolicitud,this.httpOptions2)
  }

  setEncuestados(idSolicitud:number,Ids :any,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/muestra/add/'+idSolicitud,{"encuestados":Ids},this.httpOptions2)
  }

  getParroquias(){
    return this.http.get(this.API_URL+'api/parroquia/getall',this.httpOptions);
  }

  getEncuestado(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+id,this.httpOptions2);
  }

  updateEncuestado( 
    numeroIdentificacion:string,
    primerNombre:string,
    primerApellido:string,
    genero:string,
    estadoCivil:string,
    ocupacion:string,
    parroquiaId:number,
    _codigoArea:any,
    _numeroTelefono:any,
    nombreUsuario: string,
    id:number,
    token:any
    ){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }

    return this.http.put(this.API_URL+'api/encuestado/update/'+id,
    {"encuestadoDto":
      { 
        "numeroIdentificacion": numeroIdentificacion, 
        "primerNombre": primerNombre,
        "primerApellido":primerApellido,
        "genero":genero,
        "estadoCivil":estadoCivil,
        "ocupacion":ocupacion,
        "parroquia":parroquiaId,
        "telefonos": [{
          "_codigoArea":_codigoArea,
          "_numeroTelefono":_numeroTelefono
        }]
      },
        "nombreUsuario":nombreUsuario,
        "contrasena":null
    },
          this.httpOptions2);
    }

    getAdministrador(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/administrador/getuser/'+id,this.httpOptions2);
  }

    updateAdministrador(nombreUsuario:string,contrasena:string,id:number,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.put(this.API_URL+'api/administrador/update/'+id,
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions2);
    }

    finishStudy(idStudio:number,resultado:string,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.put(this.API_URL+'api/analista/finalizar/'+idStudio,{"resultado":resultado},this.httpOptions2)
    }

    removeToken(idUsuario:number,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.put(this.API_URL+'api/auth/logout/'+idUsuario,null,this.httpOptions2)
    }
}
