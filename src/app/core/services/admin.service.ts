import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})

export class AdminService extends ApiService {
  /*Categoria*/
  createCategoria(nombreCategoria:string){
    return this.http.post(this.API_URL+'api/categoria/add',{"nombreCategoria":nombreCategoria},this.httpOptions)
  }
 
  getCategorias(){
    return this.http.get(this.API_URL+'api/categoria/getall',this.httpOptions);
  }

  /*Subcategoria*/
  createSubCategoria(nombreSubcategoria,id){
    return this.http.post(this.API_URL+'api/subcategoria/add',{"nombreSubcategoria":nombreSubcategoria,"categoria": {"id":id}},this.httpOptions)
  }

  getSubcategorias(){
    return this.http.get(this.API_URL+'api/subcategoria/getall',this.httpOptions);
  }

  /*Marca*/
  getMarcas(){
    return this.http.get(this.API_URL+'api/marca/getall',this.httpOptions);
  }

  registerBrand(nombreMarca:string,tipoMarca:string,capacidad:number,unidad:string,subcategoria:any){
    console.log(
    { 
      "nombreMarca": nombreMarca, 
      "tipoMarca": tipoMarca,
      "capacidad":capacidad,
      "unidad":unidad,
      "subcategoria": {"id":subcategoria}
  })
    return this.http.post(this.API_URL+'api/marca/add',
                      { 
                        "nombreMarca": nombreMarca, 
                        "tipoMarca": tipoMarca,
                        "capacidad":capacidad,
                        "unidad":unidad,
                        "subcategoria":{
                          "id":subcategoria}
                        },
                          this.httpOptions);
  }
}
