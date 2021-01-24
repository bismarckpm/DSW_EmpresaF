package ucab.dsw.logica.comando.categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ComandoGetCategorias implements ComandoBase {

  private JsonArrayBuilder categoriasDtos = Json.createArrayBuilder();

  public ComandoGetCategorias() {
  }

  public void execute() {

    try {

      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      List<Categoria> categorias = daoCategoria.findAll(Categoria.class);

      for(Categoria categoria:categorias){

        CategoriaDto categoriaDto = MapperCategoria.MapEntityToCategoriaDto(categoria);

        JsonObject category = Json.createObjectBuilder()
          .add("id", categoriaDto.getId())
          .add("nombreCategoria", categoriaDto.getNombreCategoria())
          .add("estado", categoriaDto.getEstado())
          .add("code", 200)
          .build();

         categoriasDtos.add(category);
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
        .add("categorias", this.categoriasDtos)
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }
}
