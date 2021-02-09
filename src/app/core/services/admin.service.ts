import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs/operators';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})

export class AdminService extends ApiService {

 
  
  createCategoria(nombreCategoria:string,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/categoria/add',{"nombreCategoria":nombreCategoria},this.httpOptions2)
  }

  updateCategoria(nombreCategoria:string,id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/categoria/update/'+id,{"nombreCategoria":nombreCategoria},this.httpOptions2)
  }
 
  getCategoria(id:number){
    return this.http.get(this.API_URL+'api/categoria/getcategoria/'+id,this.httpOptions)
  }

  getCategorias(){
    return this.http.get(this.API_URL+'api/categoria/getall',this.httpOptions);
  }

  activeCategory(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/categoria/enable/'+id,this.httpOptions2);
  }

  inactiveCategory(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/categoria/disable/'+id,this.httpOptions2);
  }

  createSubCategoria(nombreSubcategoria,id,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/subcategoria/add',{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":id}},this.httpOptions2)
  }

  updateSubcategoria(nombreSubcategoria:string,idSubcategoria:number,id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/subcategoria/update/'+id,{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":idSubcategoria}},this.httpOptions2)
  }
 
  getSubcategoria(id:number){
    return this.http.get(this.API_URL+'api/subcategoria/getsubcategoria/'+id,this.httpOptions)
  }

  getSubcategorias(){
    return this.http.get(this.API_URL+'api/subcategoria/getall',this.httpOptions);
  }

  activeSubcategory(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/subcategoria/enable/'+id,this.httpOptions2);
  }

  inactiveSubcategory(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/subcategoria/disable/'+id,this.httpOptions2);
  }

  getMarcas(){
    return this.http.get(this.API_URL+'api/marca/getall',this.httpOptions);
  }

  getMarca(id:number){
    return this.http.get(this.API_URL+'api/marca/getmarca/'+id,this.httpOptions)
  }

  createMarca(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:string,id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/marca/add',
                                                      {
                                                        "nombreMarca": nombreMarca,
                                                        "tipoMarca": tipoMarca,
                                                        "capacidad": capacidad,
                                                        "unidad": unidad,
                                                        "subcategoria":{
                                                            "id": id
                                                        }
                                                      },this.httpOptions2)
  }

  updateMarca(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:string,idsub:number,id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/marca/update/'+id,
                                                      {
                                                        "nombreMarca": nombreMarca,
                                                        "tipoMarca": tipoMarca,
                                                        "capacidad": capacidad,
                                                        "unidad": unidad,
                                                        "subcategoria":{
                                                            "id": idsub
                                                        }
                                                      },this.httpOptions2)
  }

  activeBrand(id:number){
    return this.http.put(this.API_URL+'api/marca/enable/'+id,this.httpOptions);
  }

  inactiveBrand(id:number){
    return this.http.put(this.API_URL+'api/marca/disable/'+id,this.httpOptions);
  }


  getRequestedStudies(idAdmin:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/administrador/getsolicitudespendientes/'+idAdmin,this.httpOptions2);
  }

  getStudies(token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }

    return this.http.get(this.API_URL+'api/estudio/getall',this.httpOptions2);
  }

  assignStudy(idSolicitud:number,idEstudio:number){
    return this.http.put(this.API_URL+'api/administrador/asignarsolicitud/'+idSolicitud,{"estudio": idEstudio,},this.httpOptions);
  }

  createQuestion(descripcionPregunta:string,tipoPregunta:string,min:number,max:number,opciones:any,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/preguntas',
    {"descripcionPregunta":descripcionPregunta,
    "tipoPregunta": tipoPregunta,
    "min":min,
    "max":max,
    "opciones":opciones
    },this.httpOptions2)
  }

  getQuestions(token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/preguntas',this.httpOptions2);
  }

  getQuestionsSu(idEncuesta,token:any){/*Preguntas sugeridas para la encuesta*/
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/preguntas/'+idEncuesta+'/sugerencias',this.httpOptions2);
  }

  getQuestionsNo(idEncuesta,token:any){/*Preguntas que no estan en la encuesta*/ 
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestas/'+idEncuesta+'/preguntasagregables',this.httpOptions2);
  }

  getQuestionP(id:number,token:any){/*Preguntas que esten en una encuesta*/
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestas/'+id+'/preguntas',this.httpOptions2);
  }

  setQuestions(idEncuesta:number,Ids :any,token:any){/*Agrega preguntas a una encuesta*/
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.post(this.API_URL+'api/encuestas/'+idEncuesta+'/pregunta',{"preguntas":Ids},this.httpOptions2)
  }

  addQuestiontoPoll(idEncuesta:number,idPregunta:number){/*Agrega una pregunta a la encuesta NO SE ESTA USANDO*/
    return this.http.post(this.API_URL+'api/encuestas/'+idEncuesta+'/pregunta',{"id":idPregunta},this.httpOptions);

  }

  getCliente(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/cliente/getuser/'+id,this.httpOptions2);
  }

  getClientes(token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/cliente/getall',this.httpOptions2);

  }

  getEncuestado(id:number,token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+id,this.httpOptions2);
  }

  getEncuestados(token:any){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.get(this.API_URL+'api/encuestado/getall',this.httpOptions2);
  }

  updateCliente(nombre:string,contrasena:string,id:number,token){
    this.httpOptions2 = {
      headers: new HttpHeaders({
      'authorization': token,
      })
    }
    return this.http.put(this.API_URL+'api/cliente/update/'+id,
                          {"clienteDto":
                            {
                              "nombre": nombre
                            },
                            "contrasena":contrasena
                          },
                          this.httpOptions2)
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
        "contrasena":null /*Problemas al cambiar contraseña*/
    },
          this.httpOptions2);
    }
    
    activeClient(id:number){
      return this.http.put(this.API_URL+'api/cliente/enable/'+id,this.httpOptions);
    }

    inactiveClient(id:number){
      return this.http.put(this.API_URL+'api/cliente/disable/'+id,this.httpOptions);
    }

    activeRespondent(id:number){
      return this.http.put(this.API_URL+'api/encuestado/enable/'+id,this.httpOptions);
    }

    inactiveRespondent(id:number){
      return this.http.put(this.API_URL+'api/encuestado/disable/'+id,this.httpOptions);
    }

    getParroquias(){
      return this.http.get(this.API_URL+'api/parroquia/getall',this.httpOptions);
    }

    registerCliente(nombre:string,nombreUsuario:string,contrasena:string,token){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.post(this.API_URL+'api/cliente/add',
                            {"clienteDto":
                              {
                                "nombre": nombre
                              },
                              "nombreUsuario":nombreUsuario,
                              "contrasena":contrasena
                            },
                            this.httpOptions2)
    }

    getEncuesta(){
      return this.http.get(this.API_URL+'api/encuestas',this.httpOptions);
    }

    createEncuesta(idSubcategoria:number,nombreEncuesta:string){
      return this.http.post(this.API_URL+'api/encuestas',
      { "nombreEncuesta":nombreEncuesta,
        "subcategoria":
        {
        "id":idSubcategoria
        }
      },
      this.httpOptions)
    }

    createStudy(nombreEstudio:string,idEncuesta:number){
      return this.http.post(this.API_URL+'api/estudio/add',
      { "nombreEstudio":nombreEstudio,
        "encuesta":{
          "id":idEncuesta
        }
      },
      this.httpOptions)
    }

    getAdministradores(token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.get(this.API_URL+'api/administrador/getall',this.httpOptions2);
    }

    getAdministrador(id:number,token){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.get(this.API_URL+'api/administrador/getuser/'+id,this.httpOptions2);
    }

    activeAdministrador(id:number){
      return this.http.put(this.API_URL+'api/administrador/enable/'+id,this.httpOptions);
    }

    inactiveAdministrador(id:number){
      return this.http.put(this.API_URL+'api/administrador/disable/'+id,this.httpOptions);
    }

    registerAdministrador(nombreUsuario:string,contrasena:string,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.post(this.API_URL+'api/administrador/add',
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions2);
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

    getAnalistas(token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.get(this.API_URL+'api/analista/getall',this.httpOptions2);
    }

    getAnalista(id:number,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.get(this.API_URL+'api/analista/getuser/'+id,this.httpOptions2);
    }

    activeAnalista(id:number){
      return this.http.put(this.API_URL+'api/analista/enable/'+id,this.httpOptions);
    }

    inactiveAnalista(id:number){
      return this.http.put(this.API_URL+'api/analista/disable/'+id,this.httpOptions);
    }
  
    registerAnalista(nombreUsuario:string,contrasena:string,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.post(this.API_URL+'api/analista/add',
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions2);
    }

    updateAnalista(nombreUsuario:string,contrasena:string,id:number,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      return this.http.put(this.API_URL+'api/analista/update/'+id,
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions2);
    }

    getRespuestaEncuesta(idEncuesta:number,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
  
      return this.http.get(this.API_URL+'api/encuestas/respuesta/'+idEncuesta,this.httpOptions2)
      .pipe(map(resultado => resultado))
    }

    /*Crear estudio nuevo flujo */
    newCreateStudy(nombreEstudio:string,nombreEncuesta:string,preguntas:any,idSolicitud:number,preguntaExistente:boolean,token:any){
      this.httpOptions2 = {
        headers: new HttpHeaders({
        'authorization': token,
        })
      }
      if(preguntaExistente == true){
        return this.http.post(this.API_URL+'api/estudio/add/'+idSolicitud,
                              {
                                "nombreEstudio": nombreEstudio,
                                "encuesta":{
                                    "nombreEncuesta": nombreEncuesta
                                },
                                "preguntas":preguntas
                            },
                             this.httpOptions2);
      }
      else if(preguntaExistente == false) {
        console.log({
          "nombreEstudio": nombreEstudio,
          "encuesta":{
              "nombreEncuesta": nombreEncuesta
          },
          "preguntas":preguntas
      })
        return this.http.post(this.API_URL+'api/estudio/add/'+idSolicitud,
                              {
                                "nombreEstudio": nombreEstudio,
                                "encuesta":{
                                    "nombreEncuesta": nombreEncuesta
                                },
                                "preguntas":preguntas
                            },
                            this.httpOptions2);
      }
    }
}
