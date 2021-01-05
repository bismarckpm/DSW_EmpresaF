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

  getSample(idSolicitud:number){
    return this.http.get(this.API_URL+'api/muestra/getmuestra/'+idSolicitud,this.httpOptions)
  }

  getEncuestados(idSolicitud:number){
    return this.http.get(this.API_URL+'api/muestra/usuarioagregable/'+idSolicitud,this.httpOptions)
  }

  setEncuestados(idSolicitud:number,Ids :any){
    return this.http.post(this.API_URL+'api/muestra/add/'+idSolicitud,{"encuestados":Ids},this.httpOptions)
  }

  getParroquias(){
    return this.http.get(this.API_URL+'api/parroquia/getall',this.httpOptions);
  }

  getEncuestado(id:number){
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+id,this.httpOptions);
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
    /*contrasena:string*/
    id:number
    ){

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
        "contrasena":null /*Problemas al cambiar contraseña*/
    },
          this.httpOptions);
    }

    getAdministrador(id:number){ /*Aun que digan administrator estos metodos cambian al usuario, no al administrador */
      return this.http.get(this.API_URL+'api/administrador/getuser/'+id,this.httpOptions);
    }

    updateAdministrador(nombreUsuario:string,contrasena:string,id:number){
      return this.http.put(this.API_URL+'api/administrador/update/'+id,
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions);
    }
}
