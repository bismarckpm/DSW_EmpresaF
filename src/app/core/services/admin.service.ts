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

  getRequestedStudies(){
    return this.http.get(this.API_URL+'api/solicitud/getall',this.httpOptions);
  }
}
