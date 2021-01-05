import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService extends ApiService{

  registerEncuestado( 
                    numeroIdentificacion:string,
                    primerNombre:string,
                    primerApellido:string,
                    direccionComplemento:string,
                    fechaNacimiento:string,
                    genero:string,
                    estadoCivil:string,
                    ocupacion:string,
                    parroquia:number,
                    nivelEstudio:number,
                    nivelSocioeconomico:number,
                    _codigoArea:any,
                    _numeroTelefono:any,
                    nombreUsuario:string,
                    contrasena:string
                    ){

                    return this.http.post(this.API_URL+'api/encuestado/add',
                    {"encuestadoDto":
                      { 
                        "numeroIdentificacion": numeroIdentificacion, 
                        "primerNombre": primerNombre,
                        "primerApellido":primerApellido,
                        "direccionComplemento":direccionComplemento,
                        "fechaNacimiento":fechaNacimiento,
                        "genero":genero,
                        "estadoCivil":estadoCivil,
                        "ocupacion":ocupacion,
                        "parroquia":parroquia,
                        "nivelEstudio":nivelEstudio,
                        "nivelSocioeconomico":nivelSocioeconomico,
                        "telefonos": [{
                          "_codigoArea":_codigoArea,
                          "_numeroTelefono":_numeroTelefono
                        }]
                      },
                        "nombreUsuario":nombreUsuario,
                        "contrasena":contrasena
                    },
                          this.httpOptions);
  }

  getMarcas(){
    return this.http.get(this.API_URL+'api/marca/getall',this.httpOptions);
  }

  requestStudy(edadInicial:number,edadfinal:number,genero:string,cliente:number,parroquia:number,marca:number,nivelSocioeconomico:number){
    return this.http.post(this.API_URL+'api/solicitud/add',
                          {
                          "edadInicial": edadInicial,
                          "edadfinal": edadfinal,
                          "genero": genero,
                          "cliente": cliente,
                          "parroquia": parroquia,
                          "marca": marca,
                          "nivelSocioeconomico": nivelSocioeconomico
                          },
                          this.httpOptions)
    }
    
  getParroquias(){
    return this.http.get(this.API_URL+'api/parroquia/getall',this.httpOptions);
  }

  getSpecificStudies(idSolicitud:number){
    return this.http.get(this.API_URL+'api/cliente/getsolicitudes/'+idSolicitud,this.httpOptions);
  }

  getEstudioEncuestado(idEncuestado:number){
    return this.http.get(this.API_URL+'api/encuestado/getestudios/'+idEncuestado,this.httpOptions);
  }

  getPreguntaEncuesta(idEncuesta:number){
    return this.http.get(this.API_URL+'api/encuestas/'+idEncuesta+'/preguntas',this.httpOptions);
  }

  respuestaEncuesta(respuesta:any,idEncuesta:number){
    return this.http.post(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,
    {respuestas: respuesta},this.httpOptions)
  }

  getIdEncuestado(idEncuestado:number){
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+idEncuestado,this.httpOptions);
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
