import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})

export class AdminService extends ApiService {

  createCategoria(nombreCategoria:string){
    return this.http.post(this.API_URL+'api/categoria/add',{"nombreCategoria":nombreCategoria},this.httpOptions)
  }

  updateCategoria(nombreCategoria:string,id:number){
    return this.http.put(this.API_URL+'api/categoria/update/'+id,{"nombreCategoria":nombreCategoria},this.httpOptions)
  }
 
  getCategoria(id:number){
    return this.http.get(this.API_URL+'api/categoria/getcategoria/'+id,this.httpOptions)
  }

  getCategorias(){
    return this.http.get(this.API_URL+'api/categoria/getall',this.httpOptions);
  }

  activeCategory(id:number){
    return this.http.put(this.API_URL+'api/categoria/enable/'+id,this.httpOptions);
  }

  inactiveCategory(id:number){
    return this.http.put(this.API_URL+'api/categoria/disable/'+id,this.httpOptions);
  }

  createSubCategoria(nombreSubcategoria,id){
    return this.http.post(this.API_URL+'api/subcategoria/add',{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":id}},this.httpOptions)
  }

  updateSubcategoria(nombreSubcategoria:string,idSubcategoria:number,id:number){
    return this.http.put(this.API_URL+'api/subcategoria/update/'+id,{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":idSubcategoria}},this.httpOptions)
  }
 
  getSubcategoria(id:number){
    return this.http.get(this.API_URL+'api/subcategoria/getsubcategoria/'+id,this.httpOptions)
  }

  getSubcategorias(){
    return this.http.get(this.API_URL+'api/subcategoria/getall',this.httpOptions);
  }

  activeSubcategory(id:number){
    return this.http.put(this.API_URL+'api/subcategoria/enable/'+id,this.httpOptions);
  }

  inactiveSubcategory(id:number){
    return this.http.put(this.API_URL+'api/subcategoria/disable/'+id,this.httpOptions);
  }

  getMarcas(){
    return this.http.get(this.API_URL+'api/marca/getall',this.httpOptions);
  }

  getMarca(id:number){
    return this.http.get(this.API_URL+'api/marca/getmarca/'+id,this.httpOptions)
  }

  createMarca(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:string,id:number){
    return this.http.post(this.API_URL+'api/marca/add',
                                                      {
                                                        "nombreMarca": nombreMarca,
                                                        "tipoMarca": tipoMarca,
                                                        "capacidad": capacidad,
                                                        "unidad": unidad,
                                                        "subcategoria":{
                                                            "id": id
                                                        }
                                                      },this.httpOptions)
  }

  updateMarca(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:string,idsub:number,id:number){
    return this.http.put(this.API_URL+'api/marca/update/'+id,
                                                      {
                                                        "nombreMarca": nombreMarca,
                                                        "tipoMarca": tipoMarca,
                                                        "capacidad": capacidad,
                                                        "unidad": unidad,
                                                        "subcategoria":{
                                                            "id": idsub
                                                        }
                                                      },this.httpOptions)
  }

  activeBrand(id:number){
    return this.http.put(this.API_URL+'api/marca/enable/'+id,this.httpOptions);
  }

  inactiveBrand(id:number){
    return this.http.put(this.API_URL+'api/marca/disable/'+id,this.httpOptions);
  }


  getRequestedStudies(idAdmin:number){
    return this.http.get(this.API_URL+'api/administrador/getsolicitudespendientes/'+idAdmin,this.httpOptions);
  }

  getStudies(){
    return this.http.get(this.API_URL+'api/estudio/getall',this.httpOptions);
  }

  assignStudy(idSolicitud:number,idEstudio:number){
    return this.http.put(this.API_URL+'api/administrador/asignarsolicitud/'+idSolicitud,{"estudio": idEstudio,},this.httpOptions);
  }

  createQuestion(descripcionPregunta:string,tipoPregunta:string,min:number,max:number,opciones:any){
    return this.http.post(this.API_URL+'api/preguntas',
    {"descripcionPregunta":descripcionPregunta,
    "tipoPregunta": tipoPregunta,
    "min":min,
    "max":max,
    "opciones":opciones
    },this.httpOptions)
  }

  getQuestions(){
    return this.http.get(this.API_URL+'api/preguntas',this.httpOptions);
  }

  getQuestionP(id:number){/*Preguntas que esten en una encuesta*/
    return this.http.get(this.API_URL+'api/encuestas/'+id+'/preguntas',this.httpOptions);
  }

  addQuestiontoPoll(idEncuesta:number,idPregunta:number){/*http://localhost:8081/servicio-1.0-SNAPSHOT/api/encuestas/2/pregunta  json {"id":2}*/
    return this.http.post(this.API_URL+'api/encuestas/'+idEncuesta+'/pregunta',{"id":idPregunta},this.httpOptions);

  }

  getCliente(id:number){
    return this.http.get(this.API_URL+'api/cliente/getuser/'+id,this.httpOptions);
  }

  getClientes(){
    return this.http.get(this.API_URL+'api/cliente/getall',this.httpOptions);

  }

  getEncuestado(id:number){
    return this.http.get(this.API_URL+'api/encuestado/getuser/'+id,this.httpOptions);
  }

  getEncuestados(){
    return this.http.get(this.API_URL+'api/encuestado/getall',this.httpOptions);
  }

  updateCliente(nombre:string,contrasena:string,id:number){
    return this.http.put(this.API_URL+'api/cliente/update/'+id,
                          {"clienteDto":
                            {
                              "nombre": nombre
                            },
                            "contrasena":contrasena
                          },
                          this.httpOptions)
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

    getEncuesta(){
      return this.http.get(this.API_URL+'api/encuestas',this.httpOptions);
    }

    createEncuesta(idSubcategoria:number){
      return this.http.post(this.API_URL+'api/encuestas',
      { "subcategoria":
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

    getAdministradores(){
      return this.http.get(this.API_URL+'api/administrador/getall',this.httpOptions);
    }

    getAdministrador(id:number){
      return this.http.get(this.API_URL+'api/administrador/getuser/'+id,this.httpOptions);
    }

    activeAdministrador(id:number){
      return this.http.put(this.API_URL+'api/administrador/enable/'+id,this.httpOptions);
    }

    inactiveAdministrador(id:number){
      return this.http.put(this.API_URL+'api/administrador/disable/'+id,this.httpOptions);
    }

    registerAdministrador(nombreUsuario:string,contrasena:string){
      return this.http.post(this.API_URL+'api/administrador/add',
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions);
    }

    updateAdministrador(nombreUsuario:string,contrasena:string,id:number){
      return this.http.put(this.API_URL+'api/administrador/update/'+id,
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions);
    }

    getAnalistas(){
      return this.http.get(this.API_URL+'api/analista/getall',this.httpOptions);
    }

    getAnalista(id:number){
      return this.http.get(this.API_URL+'api/analista/getuser/'+id,this.httpOptions);
    }

    activeAnalista(id:number){
      return this.http.put(this.API_URL+'api/analista/enable/'+id,this.httpOptions);
    }

    inactiveAnalista(id:number){
      return this.http.put(this.API_URL+'api/analista/disable/'+id,this.httpOptions);
    }
  
    registerAnalista(nombreUsuario:string,contrasena:string){
      return this.http.post(this.API_URL+'api/analista/add',
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions);
    }

    updateAnalista(nombreUsuario:string,contrasena:string,id:number){
      return this.http.put(this.API_URL+'api/analista/update/'+id,
                            {"nombreUsuario":nombreUsuario,
                             "contrasena":contrasena},
                             this.httpOptions);
    }
}
