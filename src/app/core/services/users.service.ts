import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { HttpClient,HttpHeaders } from '@angular/common/http';

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

  requestStudy(edadInicial:number,edadfinal:number,genero:string,cliente:number,parroquia:number,subcategoria:number,nivelSocioeconomico:number,token){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/solicitud/add',
                          {
                          "edadInicial": edadInicial,
                          "edadfinal": edadfinal,
                          "genero": genero,
                          "cliente": cliente,
                          "parroquia": parroquia,
                          "subcategoria": subcategoria,
                          "nivelSocioeconomico": nivelSocioeconomico
                          },
                          this.httpOptions2)
    }
    
  getParroquias(){
    return this.http.get(this.API_URL+'api/parroquia/getall',this.httpOptions);
  }

  getSpecificStudies(idSolicitud:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/cliente/getsolicitudes/'+idSolicitud,this.httpOptions2);
  }

  getEstudioEncuestado(idEncuestado:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestado/getestudios/'+idEncuestado,this.httpOptions2);
  }

  getPreguntaEncuesta(idEncuesta:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestas/'+idEncuesta+'/preguntas',this.httpOptions2);
  }

  respuestaEncuesta(respuesta:any,idEncuesta:number,solicitudId,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,
                                                                            {"solicitudEstudioDto":
                                                                              {
                                                                                "id": solicitudId
                                                                              },
                                                                              respuestas: respuesta
                                                                            },this.httpOptions2)
  }

  getIdEncuestado(idEncuestado:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+idEncuestado,this.httpOptions2);
  }

  getAdministrador(id:number,token:any){ /*Aun que digan administrator estos metodos cambian al usuario, no al administrador */
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

  getSubcategorias(){
    return this.http.get(this.API_URL+'api/subcategoria/getall',this.httpOptions);
  }

  getRespuestaEncuesta(idEncuesta:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,this.httpOptions2)
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

