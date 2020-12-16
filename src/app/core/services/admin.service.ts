import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})

export class AdminService extends ApiService {

  createCategoria(nombreCategoria:string){
    return this.http.post(this.API_URL+'api/categoria/add',{"nombreCategoria":nombreCategoria},this.httpOptions)
  }
 
  getCategorias(){
    return this.http.get(this.API_URL+'api/categoria/getall',this.httpOptions);
  }

  createSubCategoria(nombreSubcategoria,id){
    return this.http.post(this.API_URL+'api/subcategoria/add',{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":id}},this.httpOptions)
  }

  getSubcategorias(){
    return this.http.get(this.API_URL+'api/subcategoria/getall',this.httpOptions);
  }

  getMarcas(){
    return this.http.get(this.API_URL+'api/marca/getall',this.httpOptions);
  }

  createMarca(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:number,id:number){
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
    return this.http.post(this.API_URL+'api/preguntas/add',
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
      console.log({"encuestadoDto":
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
        "contrasena":null 
    })

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
}
