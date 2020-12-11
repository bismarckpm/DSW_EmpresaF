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

                      console.log( {"encuestadoDto":
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
                    })
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

  registerCliente(nombre:string,nombreUsuario:string,contrasena:string){
    return this.http.post(this.API_URL+'api/cliente/add',
                          {"clienteDto":
                            {
                              "nombre": nombre
                            },
                            "nombreUsuario":nombreUsuario,
                            "contrasena":contrasena
                          },
                          this.httpOptions)
  }
}
