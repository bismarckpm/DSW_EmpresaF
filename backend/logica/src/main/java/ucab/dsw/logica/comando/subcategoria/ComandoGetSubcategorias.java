package ucab.dsw.logica.comando.subcategoria;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.subcategoria.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetSubcategorias implements ComandoBase {

  private JsonArrayBuilder subcategoriasDtos = Json.createArrayBuilder();

  public ComandoGetSubcategorias() {
  }

  public void execute() {

    try {

      DaoSubcategoria daoSubategoria = Fabrica.crear(DaoSubcategoria.class);
      List<Subcategoria> subcategorias = daoSubategoria.findAll(Subcategoria.class);

      for(Subcategoria subcategoria:subcategorias){

        SubcategoriaDto subcategoriaDto = MapperSubcategoria.MapEntityToSubcategoriaDto(subcategoria);

        JsonObject subcategory = Json.createObjectBuilder()
          .add("id", subcategoriaDto.getId())
          .add("nombreSubcategoria", subcategoriaDto.getNombreSubcategoria())
          .add("estado", subcategoriaDto.getEstado())
          .add("categoriaId", subcategoriaDto.getCategoria().getId())
          .add("categoria", subcategoriaDto.getCategoria().getNombreCategoria())
          .build();

        subcategoriasDtos.add(subcategory);
      }

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("code", 200)
        .add("estado", "success")
        .add("subcategorias", this.subcategoriasDtos)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }

}
