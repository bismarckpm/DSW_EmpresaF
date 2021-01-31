package ucab.dsw.logica.comando.subcategoria;

import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoGetSubcategoria implements ComandoBase{

  private  long idSubcategoria;
  private SubcategoriaDto subcategoriaDto;

  public ComandoGetSubcategoria(long idSubcategoria){this.idSubcategoria = idSubcategoria;}


  public  void execute(){
    try{

      DaoSubcategoria daoSubcategoria = Fabrica.crear(DaoSubcategoria.class);
      Subcategoria resultado = daoSubcategoria.find(this.idSubcategoria,Subcategoria.class);

      this.subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){
    try{
      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", this.subcategoriaDto.getId())
        .add("nombreSubcategoria", this.subcategoriaDto.getNombreSubcategoria())
        .add("estadoSubcategoria", this.subcategoriaDto.getEstado())
        .add("categoriaId", this.subcategoriaDto.getCategoria().getId())
        .add("categoriaNombre", this.subcategoriaDto.getCategoria().getNombreCategoria())
        .build();

      return data;
    }catch (Exception ex){
      throw ex;
    }

  }

}
