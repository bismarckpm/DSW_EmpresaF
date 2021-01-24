package ucab.dsw.logica.comando.categoria;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.logica.comando.ComandoBase;
import ucab.dsw.logica.fabrica.Fabrica;
import ucab.sw.mapper.categoria.MapperCategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ComandoGetCategoria implements ComandoBase {

  private long idCategoria;
  private CategoriaDto categoriaDto;

  public ComandoGetCategoria(long idCategoria) {
    this.idCategoria = idCategoria;
  }

  public void execute() {

    try {

      DaoCategoria daoCategoria = Fabrica.crear(DaoCategoria.class);
      Categoria resultado = daoCategoria.find(this.idCategoria, Categoria.class);

      this.categoriaDto = MapperCategoria.MapEntityToCategoriaDto(resultado);

    }catch (Exception ex){
      throw ex;
    }

  }

  public JsonObject getResultado(){

    try{

      JsonObject data = Json.createObjectBuilder()
        .add("estado", "success")
        .add("code", 200)
        .add("id", this.categoriaDto.getId())
        .add("nombreCategoria", this.categoriaDto.getNombreCategoria())
        .add("estadoCategoria", this.categoriaDto.getEstado())
        .build();

      return data;

    }catch (Exception ex){
      throw ex;
    }

  }
}
